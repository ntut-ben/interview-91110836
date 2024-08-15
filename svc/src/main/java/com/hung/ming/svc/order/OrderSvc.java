package com.hung.ming.svc.order;

import com.hung.ming.repo.order.dao.IOrderDao;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import com.hung.ming.repo.order.repo.IOrderRepo;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.command.GetMemberOrderStatisticsCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderSvc implements IOrderSvc {

  private final IOrderRepo orderRepo;

  private final IOrderDao orderDao;

  @Override
  public List<MemberOrderBean> getMemberOrderStatistics(GetMemberOrderStatisticsCommand command) {
    List<MemberOrderDto> dtos = orderDao.getMemberOrderStatistics(command.getCount());
    if (CollectionUtils.isEmpty(dtos)) {
      return Collections.emptyList();
    } else {
      return dtos.stream().map(dto -> {
        MemberOrderBean bean = new MemberOrderBean();
        PropertyUtilsProxy.copyProperties(bean, dto);
        return bean;
      }).toList();
    }
  }
}
