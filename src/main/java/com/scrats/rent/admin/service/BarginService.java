package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.PageInfo;
import com.scrats.rent.admin.entity.Bargin;
import com.scrats.rent.admin.mapper.BarginMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface BarginService extends BaseService<Bargin, BarginMapper> {

    List<Bargin> getBarginByRoomId(Integer roomId, boolean deleteFlag);

    PageInfo<Bargin> getBarginList(APIRequest apiRequest, Bargin bargin);

    PageInfo<Bargin> getBarginList(APIRequest apiRequest, Bargin bargin, boolean pageFlag);

    Bargin getRoomBargin(Integer roomId);

    Bargin getRoomBarginDetail(Integer roomId);

}
