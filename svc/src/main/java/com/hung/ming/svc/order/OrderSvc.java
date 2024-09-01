package com.hung.ming.svc.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hung.ming.repo.dto.MemberOrderDto;
import com.hung.ming.repo.dto.ProductDto;
import com.hung.ming.repo.entity.Order;
import com.hung.ming.repo.entity.OrderProduct;
import com.hung.ming.repo.entity.OrderProductId;
import com.hung.ming.repo.entity.Product;
import com.hung.ming.repo.mapper.IOrderMapper;
import com.hung.ming.repo.mapper.IOrderProductMapper;
import com.hung.ming.repo.mapper.IProductMapper;
import com.hung.ming.repo.query.QGetOrderQuery;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.bean.OrderBean;
import com.hung.ming.svc.order.bean.ProductBean;
import com.hung.ming.svc.order.command.OrderCommand;
import com.hung.ming.svc.order.query.GetMemberOrderStatisticsQuery;
import com.hung.ming.svc.order.query.GetOrderQuery;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderSvc implements IOrderSvc {

  private final IOrderMapper orderMapper;

  private final IProductMapper productMapper;

  private final IOrderProductMapper orderProductMapper;


  @Override
  public List<MemberOrderBean> getMemberOrderStatistics(GetMemberOrderStatisticsQuery query) {
    List<MemberOrderDto> dtos = orderMapper.getMemberOrderStatistics(query.getCount());
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
  public PageInfo<OrderBean> getOrders(GetOrderQuery query, Pageable pageable) {
    QGetOrderQuery getOrderQuery = new QGetOrderQuery();
    PropertyUtilsProxy.copyProperties(getOrderQuery, query);


    PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
    List<Order> orders = orderMapper.getOrders(getOrderQuery);
    PageInfo<Order> orderPage = new PageInfo<>(orders);

    if (orderPage.hasContent()) {
      List<String> orderIds = orderPage.getList().stream().map(Order::getId).toList();
      List<ProductDto> productDtos = productMapper.getProductByOrderId(orderIds);
      return orderPage.convert(order -> {
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

    return PageInfo.emptyPageInfo();
  }

  @Transactional
  @Override
  public String order(OrderCommand command) {
    // 取出產品(鎖)
    Product product = productMapper.findByIdWithLock(command.getProductId());

    if (product.getStock() >= command.getQuantity()) {
      product.setStock(product.getStock() - command.getQuantity());
      productMapper.save(product);
    } else {
      throw new RuntimeException("庫存不足");
    }

    BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(command.getQuantity()));
    // 無訂單編號建立新訂單
    Order order;
    Timestamp now = new Timestamp(System.currentTimeMillis());
    if (command.getOrderId() == null) {
      order = new Order();
      order.setOrderDate(now);
      order.setId(UUID.randomUUID().toString());
      order.setStatus("PENDING");
      order.setCreatedTime(now);
      order.setUpdateTime(now);
      order.setMemberId(command.getMemberId());
      order.setTotalAmount(subTotal);
      orderMapper.save(order);
    } else {
      // 有訂單編號更新訂單（鎖）
      order = orderMapper.findByIdWithLock(command.getOrderId());
      order.setUpdateTime(now);
      order.setTotalAmount(order.getTotalAmount().add(subTotal));
      orderMapper.update(order);
    }

    // 取出原訂單明細（鎖）
    OrderProductId orderProductId = new OrderProductId();
    orderProductId.setOrderId(order.getId());
    orderProductId.setProductId(product.getId());
    Optional<OrderProduct> orderProductOptional =
        orderProductMapper.findByIdWithLock(orderProductId);

    orderProductOptional.ifPresentOrElse(orderProduct -> {
      orderProduct.setPrice(orderProduct.getPrice().add(subTotal));
      orderProduct.setQuantity(orderProduct.getQuantity() + command.getQuantity());
      orderProductMapper.update(orderProduct);
    }, () -> saveOrderProduct(orderProductId, subTotal, command.getQuantity()));

    return order.getId();
  }

  private void saveOrderProduct(OrderProductId orderProductId, BigDecimal subTotal, int quantity) {
    OrderProduct orderProduct = new OrderProduct();
    orderProduct.setId(orderProductId);
    orderProduct.setPrice(subTotal);
    orderProduct.setQuantity(quantity);
    orderProductMapper.save(orderProduct);
  }
}
