package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.entity.Bargin;
import com.scrats.rent.admin.entity.BarginExtra;
import com.scrats.rent.admin.mapper.BarginExtraMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface BarginExtraService extends BaseService<BarginExtra, BarginExtraMapper> {

    List<BarginExtra> getBarginExtraTypeByBargin(Bargin bargin);

    List<BarginExtra> getBarginExtraByBuildingIdAndDicItermCode(Integer buildingId, String code);
}
