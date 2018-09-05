package com.scrats.rent.admin.service.impl;

import com.scrats.rent.admin.base.service.BaseServiceImpl;
import com.scrats.rent.admin.entity.Bargin;
import com.scrats.rent.admin.entity.BarginExtra;
import com.scrats.rent.admin.mapper.BarginExtraMapper;
import com.scrats.rent.admin.service.BarginExtraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class BarginExtraServiceImpl extends BaseServiceImpl<BarginExtra, BarginExtraMapper> implements BarginExtraService {

    @Override
    public List<BarginExtra> getBarginExtraTypeByBargin(Bargin bargin) {
        return dao.getBarginExtraTypeByBargin(bargin);
    }

    @Override
    public List<BarginExtra> getBarginExtraByBuildingIdAndDicItermCode(Integer buildingId, String code) {
        return dao.getBarginExtraByBuildingIdAndDicItermCode(buildingId, code);
    }
}
