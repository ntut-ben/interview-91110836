package com.hung.ming.repo.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderProductId {
  @Column(name = "ORDER_ID")
  private String orderId;

  @Column(name = "PRODUCT_ID")
  private String productId;
}
