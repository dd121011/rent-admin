package com.scrats.rent.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.admin.base.service.RedisService;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.exception.BusinessException;
import com.scrats.rent.admin.constant.GlobalConst;
import com.scrats.rent.admin.constant.SexType;
import com.scrats.rent.admin.constant.UserType;
import com.scrats.rent.admin.entity.*;
import com.scrats.rent.admin.service.*;
import com.scrats.rent.admin.util.BaseUtil;
import com.scrats.rent.admin.util.DateUtils;
import com.scrats.rent.admin.util.weixin.sns.WxAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class RenterServiceImpl implements RenterService {

    @Autowired
    private WxAuthorize wxAuthorize;
    @Autowired
    private WxSnsService wxSnsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRenterService roomRenterService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private BarginService barginService;
    @Autowired
    private BuildingService buildingService;

    @Override
    public JsonResult snsLogin(String code, String signature, String rawData) {

        WxSns checkSns = wxAuthorize.checkUserInfoFromWx(code);
        if(null == checkSns){
            return new JsonResult("获取签名失败");
        }
        log.info("checkSns is: " + JSON.toJSONString(checkSns));
        //检查signature的正确性
        log.info("request signature is: " + signature);
        String newSignature = BaseUtil.getSha1(rawData+checkSns.getSession_key());
        log.info("check signature is: " + newSignature);
        if(!signature.equals(newSignature)){
            return new JsonResult("签名不正确");
        }
        //生成token,保存登录
        WxSns wxSns = wxSnsService.selectByPrimaryKey(checkSns.getOpenid());
        log.info("wxSns login: " + JSON.toJSONString(wxSns));
        String tokenId = UUID.randomUUID().toString().replace("-","");
        JSONObject result = new JSONObject();
        result.put("tokenId", tokenId);
        if(null == wxSns){
            checkSns.setCreateTs(System.currentTimeMillis());
            wxSnsService.insertSelective(checkSns);
            checkSns.setUserId(-1);
            result.put("wxSns", checkSns);
            redisService.set(checkSns.getOpenid(),tokenId, GlobalConst.SNS_FIRST_ACCESS_TOKEN_EXPIRE);
            return new JsonResult<JSONObject>(result);
        }else{
            if(null!= wxSns.getUserId() && wxSns.getUserId() > 0){
                //已有userId，保存登录状态
                User user = userService.selectByPrimaryKey(wxSns.getUserId());
                if(null == user){
                    throw new BusinessException("系统数据异常!");
                }
                redisService.set(tokenId,user, GlobalConst.ACCESS_TOKEN_EXPIRE);
            }else{
                wxSns.setUserId(-1);
                redisService.set(checkSns.getOpenid(),tokenId, GlobalConst.SNS_FIRST_ACCESS_TOKEN_EXPIRE);
            }
            result.put("wxSns", wxSns);
            return new JsonResult<JSONObject>(result);
        }
    }

    @Override
    public JSONArray getRoomList(Integer userId) {
        Date date = new Date();
        JSONArray result = new JSONArray();
        RoomRenter param = new RoomRenter();
        param.setUserId(userId);
        param.setCheckTs(1L);
        List<RoomRenter> rrlist = roomRenterService.getListByRoomrenter(param);
        for(RoomRenter rr :  rrlist){
            JSONObject jsonObject = new JSONObject();
            Room room = roomService.selectByPrimaryKey(rr.getRoomId());
            Bargin bargin = barginService.selectByPrimaryKey(rr.getBarginId());
            Building building = buildingService.selectByPrimaryKey(room.getBuildingId());
            Date rentDay = this.getPayTime(date,bargin.getRentDay());
            String payStatus = "pay";
            if(null != room.getRentList() && room.getRentList().size() > 0){
                payStatus = "unpay";
            }
            jsonObject.put("roomId", room.getRoomId());
            jsonObject.put("barginId", rr.getBarginId());
            jsonObject.put("roomNo", room.getRoomNo());
            jsonObject.put("buildingName", building.getName());
            jsonObject.put("nextTime", rentDay.getTime()-date.getTime());
            jsonObject.put("payTime", rentDay.getTime());
            jsonObject.put("payStatus", payStatus);
            result.add(jsonObject);
        }
        return result;
    }

    @Override
    @Transactional
    public JsonResult snsRenterRegist(String tokenId, String openid, String name, String phone, String idCard) {

        if(null != accountService.findBy("phone",phone)){

            return new JsonResult("手机号:" + phone + "在系统中已被注册");
        }
        if(null != userService.findBy("idCard",idCard)){

            return new JsonResult("身份证号:" + idCard + "在系统中已被注册");
        }

        Long createTs = System.currentTimeMillis();

        //创建account
        Account account = new Account(phone, phone, phone);
        account.setCreateTs(createTs);
        accountService.insertSelective(account);

        //创建user
        User user = new User(name, SexType.secret, phone, idCard);
        user.setAccountId(account.getAccountId());
        user.setCreateTs(createTs);
        userService.insertSelective(user);

        //创建userRole
        UserRole userRole = new UserRole(UserType.renter, user.getUserId());
        userRole.setCreateTs(createTs);
        userRoleService.insertSelective(userRole);

        WxSns wxSns = wxSnsService.selectByPrimaryKey(openid);
        wxSns.setUserId(user.getUserId());
        wxSns.setUpdateTs(createTs);
        wxSnsService.updateByPrimaryKeySelective(wxSns);

        //缓存user
        redisService.set(tokenId, user, GlobalConst.ACCESS_TOKEN_EXPIRE);

        JSONObject result = new JSONObject();
        result.put("tokenId", tokenId);
        result.put("wxSns", wxSns);
        return new JsonResult<JSONObject>(result);
    }

    private Date getPayTime(Date date, int rentDay){
        Date rent = DateUtils.oneDayOfThisMonth(date, rentDay);
        if(rent.getTime() - date.getTime() < 0){
            rent = DateUtils.oneDayOfNextMonth(date, rentDay);
        }
        return rent;
    }
}
