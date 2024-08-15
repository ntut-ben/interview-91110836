package com.hung.ming.rest.order;

import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.rest.order.req.GetMemberOrderStatisticsReq;
import com.hung.ming.rest.order.resp.MemberOrderVo;
import com.hung.ming.rest.order.resp.OrderVo;
import com.hung.ming.svc.order.IOrderSvc;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.command.GetMemberOrderStatisticsCommand;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String order() {
    //      3. 請設計一個訂單訂購api，會員可以訂購產品。
    return "order";
  }

  @GetMapping("/orders")
  public Page<OrderVo> getOrders() {
    //      4. 請設計一個訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。
    return Page.empty();
  }

  @GetMapping("/statistics")
  public List<MemberOrderVo> getMemberOrderStatistics(GetMemberOrderStatisticsReq req) {
    GetMemberOrderStatisticsCommand command = new GetMemberOrderStatisticsCommand(req.getCount());
    List<MemberOrderBean> beans = orderSvc.getMemberOrderStatistics(command);

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
