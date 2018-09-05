package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.PageInfo;
import com.scrats.rent.admin.entity.ExtraHistory;
import com.scrats.rent.admin.entity.Rent;
import com.scrats.rent.admin.entity.User;
import com.scrats.rent.admin.mapper.RentMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RentService extends BaseService<Rent, RentMapper> {

    List<Rent> getRentByRoomId(Integer roomId, boolean payFlag);

    List<Rent> getListByRent(Rent rent);

    List<Rent> getRentByBuildingIdandPayFlag(Integer buildingId, boolean payFlag);

    PageInfo<Rent> getRentPageList(APIRequest apiRequest, Rent rent);

    PageInfo<Rent> getRentPageList(APIRequest apiRequest, Rent rent, boolean pageFlag);

    boolean pay(User landlord, Integer rentId, String channel);

    Rent detail(Integer rentId);

    JsonResult rentEdit(Rent rent, List<ExtraHistory> extraHistoryList);

    List<Rent> payedWithRange(Long fromTs, Long toTs, Rent rent);
}
