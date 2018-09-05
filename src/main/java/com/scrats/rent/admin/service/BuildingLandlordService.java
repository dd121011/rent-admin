package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.entity.BuildingLandlord;
import com.scrats.rent.admin.mapper.BuildingLandlordMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface BuildingLandlordService extends BaseService<BuildingLandlord, BuildingLandlordMapper> {

    int deleteBuildingByLandloordIds(Integer... ids);
}
