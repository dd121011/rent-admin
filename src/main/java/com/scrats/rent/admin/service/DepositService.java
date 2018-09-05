package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.entity.Deposit;
import com.scrats.rent.admin.mapper.DepositMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface DepositService extends BaseService<Deposit, DepositMapper> {

    List<Deposit> getDepositByRoomId(Integer roomId, boolean deleteFlag);

    List<Deposit> getUnpayDeposit(Integer roomId);

    List<Deposit> payedWithRange(Long fromTs, Long toTs, Deposit deposit);

    Deposit getRoomDeposit(Integer roomId);
}
