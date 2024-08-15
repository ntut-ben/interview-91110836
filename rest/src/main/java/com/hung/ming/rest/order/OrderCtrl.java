package com.hung.ming.rest.order;

import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.rest.order.req.GetMemberOrderStatisticsReq;
import com.hung.ming.rest.order.req.GetOrderReq;
import com.hung.ming.rest.order.req.OrderReq;
import com.hung.ming.rest.order.resp.MemberOrderVo;
import com.hung.ming.rest.order.resp.OrderVo;
import com.hung.ming.svc.order.IOrderSvc;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.bean.OrderBean;
import com.hung.ming.svc.order.command.OrderCommand;
import com.hung.ming.svc.order.query.GetMemberOrderStatisticsQuery;
import com.hung.ming.svc.order.query.GetOrderQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCtrl {

  private final IOrderSvc orderSvc;

  @PostMapping("/order")
  public String order(@RequestBody OrderReq req) {
    OrderCommand command = new OrderCommand();
    PropertyUtilsProxy.copyProperties(command, req);
    return orderSvc.order(command);
  }

  @GetMapping("/orders")
  public Page<OrderVo> getOrders(GetOrderReq req) {
    GetOrderQuery query = new GetOrderQuery();
    PropertyUtilsProxy.copyProperties(query, req);
    Page<OrderBean> page = orderSvc.getOrders(query, req.pageable());

    if (page.hasContent()) {
      return page.map(bean -> {
        OrderVo vo = new OrderVo();
        PropertyUtilsProxy.copyProperties(vo, bean);
        return vo;
      });
    }
    return Page.empty();
  }

  @GetMapping("/statistics")
  public List<MemberOrderVo> getMemberOrderStatistics(GetMemberOrderStatisticsReq req) {
    GetMemberOrderStatisticsQuery query = new GetMemberOrderStatisticsQuery(req.getCount());
    List<MemberOrderBean> beans = orderSvc.getMemberOrderStatistics(query);

    if (CollectionUtils.isNotEmpty(beans)) {
      return beans.stream()
          .map(bean -> {
            MemberOrderVo vo = new MemberOrderVo();
            PropertyUtilsProxy.copyProperties(vo, bean);
            return vo;
          }).toList();
    }

    return Collections.emptyList();
  }
}
