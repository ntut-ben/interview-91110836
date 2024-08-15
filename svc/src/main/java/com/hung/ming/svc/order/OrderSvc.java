package com.hung.ming.svc.order;

import com.hung.ming.repo.order.dao.IOrderDao;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import com.hung.ming.repo.order.dto.ProductDto;
import com.hung.ming.repo.order.entity.Order;
import com.hung.ming.repo.order.entity.OrderProduct;
import com.hung.ming.repo.order.entity.OrderProductId;
import com.hung.ming.repo.order.repo.IOrderProductRepo;
import com.hung.ming.repo.order.repo.IOrderRepo;
import com.hung.ming.repo.product.entity.Product;
import com.hung.ming.repo.product.repo.IProductRepo;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.svc.order.bean.MemberOrderBean;
import com.hung.ming.svc.order.bean.OrderBean;
import com.hung.ming.svc.order.bean.ProductBean;
import com.hung.ming.svc.order.command.OrderCommand;
import com.hung.ming.svc.order.query.GetMemberOrderStatisticsQuery;
import com.hung.ming.svc.order.query.GetOrderQuery;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
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

  private final IOrderRepo orderRepo;

  private final IOrderDao orderDao;

  private final IProductRepo productRepo;

  private final IOrderProductRepo orderProductRepo;

  private final EntityManager entityManager;

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

  @Transactional
  @Override
  public String order(OrderCommand command) {
    // 取出產品(鎖)
    Product product = productRepo.findByIdWithLock(command.getProductId());
    entityManager.detach(product);

    if (product.getStock() >= command.getQuantity()) {
      product.setStock(product.getStock() - command.getQuantity());
      productRepo.save(product);
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
    } else {
      // 有訂單編號更新訂單（鎖）
      order = orderRepo.findByIdWithLock(command.getOrderId());
      entityManager.detach(order);

      order.setUpdateTime(now);
      order.setTotalAmount(order.getTotalAmount().add(subTotal));

    }

    // 取出原訂單明細（鎖）
    OrderProductId orderProductId = new OrderProductId();
    orderProductId.setOrderId(order.getId());
    orderProductId.setProductId(product.getId());
    Optional<OrderProduct> orderProductOptional =
        orderProductRepo.findByIdWithLock(orderProductId);

    orderProductOptional.ifPresentOrElse(orderProduct -> {
      entityManager.detach(orderProduct);
      orderProduct.setPrice(orderProduct.getPrice().add(subTotal));
      orderProduct.setQuantity(orderProduct.getQuantity() + command.getQuantity());
      orderProductRepo.save(orderProduct);
    }, () -> saveOrderProduct(orderProductId, subTotal, command.getQuantity()));

    orderRepo.save(order);

    return order.getId();
  }

  private void saveOrderProduct(OrderProductId orderProductId, BigDecimal subTotal, int quantity) {
    OrderProduct orderProduct = new OrderProduct();
    orderProduct.setId(orderProductId);
    orderProduct.setPrice(subTotal);
    orderProduct.setQuantity(quantity);
    orderProductRepo.save(orderProduct);
  }
}
