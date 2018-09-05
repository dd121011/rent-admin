package com.scrats.rent.admin.service.impl;

import com.scrats.rent.admin.base.service.BaseServiceImpl;
import com.scrats.rent.admin.entity.BarginExtra;
import com.scrats.rent.admin.entity.ExtraHistory;
import com.scrats.rent.admin.mapper.ExtraHistoryMapper;
import com.scrats.rent.admin.service.ExtraHistoryService;
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
public class ExtraHistoryServiceImpl extends BaseServiceImpl<ExtraHistory, ExtraHistoryMapper> implements ExtraHistoryService {

    @Override
    public List<ExtraHistory> getListByExtraHistory(ExtraHistory extraHistory) {
        return dao.getListByExtraHistory(extraHistory);
    }

    @Override
    public List<ExtraHistory> getRentEditExtraHistory(Integer rentId) {
        return dao.getRentEditExtraHistory(rentId);
    }

    @Override
    public List<ExtraHistory> getListByBarginExtraAndMonth(BarginExtra barginExtra, String month) {
        return dao.getListByBarginExtraAndMonth(barginExtra, month);
    }
}
