package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.PageInfo;
import com.scrats.rent.admin.entity.Bargin;
import com.scrats.rent.admin.entity.ExtraHistory;
import com.scrats.rent.admin.entity.Room;
import com.scrats.rent.admin.entity.User;
import com.scrats.rent.admin.mapper.RoomMapper;

import java.text.ParseException;
import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface RoomService extends BaseService<Room, RoomMapper> {

    PageInfo<Room> getRoomList(APIRequest apiRequest, Room room);

    PageInfo<Room> getRoomList(APIRequest apiRequest, Room room, boolean pageFlag);

    int deleteRoomByIds(Integer... ids);

    JsonResult rent(Bargin bargin);

    Room detail(Integer roomId);

    List<Room> getRoomByRoomNoAndBuildingId(String roomNo, Integer buildingId);

    JsonResult charge(List<ExtraHistory> chargeList, String month, Integer barginId, Integer roomId, String remark);

    JsonResult addMulity(Room room);

    JsonResult renterAdd(Integer roomId, User newUser) throws ParseException;

    JsonResult rentLeave(Integer roomId);


}
