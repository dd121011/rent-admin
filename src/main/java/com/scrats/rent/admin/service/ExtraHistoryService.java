package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.entity.BarginExtra;
import com.scrats.rent.admin.entity.ExtraHistory;
import com.scrats.rent.admin.mapper.ExtraHistoryMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface ExtraHistoryService extends BaseService<ExtraHistory, ExtraHistoryMapper> {

    List<ExtraHistory> getListByExtraHistory(ExtraHistory extraHistory);

    List<ExtraHistory> getRentEditExtraHistory(Integer rentId);

    List<ExtraHistory> getListByBarginExtraAndMonth(BarginExtra barginExtra, String month);

}
