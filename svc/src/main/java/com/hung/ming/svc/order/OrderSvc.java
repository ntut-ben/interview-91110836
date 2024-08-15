package com.hung.ming.svc.order;

import com.hung.ming.repo.order.dao.IOrderDao;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import com.hung.ming.repo.order.dto.ProductDto;
import com.hung.ming.repo.order.entity.Order;
import com.hung.ming.repo.order.entity.Product;
import com.hung.ming.repo.order.repo.IOrderRepo;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.bean.OrderBean;
import com.hung.ming.svc.order.bean.ProductBean;
import com.hung.ming.svc.order.query.GetMemberOrderStatisticsQuery;
import com.hung.ming.svc.order.query.GetOrderQuery;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderSvc implements IOrderSvc {

  private final IOrderRepo orderRepo;

  private final IOrderDao orderDao;

  @Override
  public List<MemberOrderBean> getMemberOrderStatistics(GetMemberOrderStatisticsQuery query) {
    List<MemberOrderDto> dtos = orderDao.getMemberOrderStatistics(query.getCount());
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

  @Override
  public Page<OrderBean> getOrders(GetOrderQuery query, Pageable pageable) {
    Page<Order> orders =
        orderDao.getOrders(query.getOrderNo(), query.getOrderCreateDate(), query.getProductName(),
            pageable);

    if (orders.hasContent()) {
      List<String> orderIds = orders.getContent().stream().map(Order::getId).toList();
      List<ProductDto> productDtos = orderDao.getOrderProducts(orderIds);
      return orders.map(order -> {
        OrderBean orderBean = new OrderBean();
        PropertyUtilsProxy.copyProperties(orderBean, order);
        List<ProductBean> productBeans =
            productDtos.stream().filter(productDto -> productDto.getOrderId().equals(order.getId()))
                .map(productDto -> {
                  ProductBean productBean = new ProductBean();
                  PropertyUtilsProxy.copyProperties(productBean, productDto);
                  return productBean;
                }).toList();

        orderBean.setProducts(productBeans);
        return orderBean;
      });
    }

    return Page.empty();
  }
}
