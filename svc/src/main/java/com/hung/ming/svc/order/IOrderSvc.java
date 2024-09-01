package com.hung.ming.svc.order;

import com.github.pagehelper.PageInfo;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.bean.OrderBean;
import com.hung.ming.svc.order.command.OrderCommand;
import com.hung.ming.svc.order.query.GetMemberOrderStatisticsQuery;
import com.hung.ming.svc.order.query.GetOrderQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IOrderSvc {
  PageInfo<OrderBean> getOrders(GetOrderQuery query, Pageable pageable);

  String order(OrderCommand command);

  List<MemberOrderBean> getMemberOrderStatistics(GetMemberOrderStatisticsQuery query);
}
