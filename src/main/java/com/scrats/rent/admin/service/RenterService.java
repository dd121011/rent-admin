package com.scrats.rent.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.scrats.rent.admin.common.JsonResult;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RenterService {

    JsonResult snsLogin(String code, String signature, String rawData);

    JSONArray getRoomList(Integer userId);

    JsonResult snsRenterRegist(String tokenId, String openid, String name, String phone, String idCard);

}
