package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.entity.RoomRenter;
import com.scrats.rent.admin.mapper.RoomRenterMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RoomRenterService extends BaseService<RoomRenter, RoomRenterMapper> {

    int deleteRoomRenterById(Integer... ids);

    List<RoomRenter> getListByRoomrenter(RoomRenter roomRenter);

    List<RoomRenter> getRoomRenterByBuildingId(Integer buildingId);

    int renterCheck(Integer roomRenterId);

}
