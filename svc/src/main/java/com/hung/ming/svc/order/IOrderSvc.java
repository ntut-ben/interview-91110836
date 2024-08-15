package com.hung.ming.svc.order;

import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.command.GetMemberOrderStatisticsCommand;

import java.util.List;

public interface IOrderSvc {
  List<MemberOrderBean> getMemberOrderStatistics(GetMemberOrderStatisticsCommand command);
}
