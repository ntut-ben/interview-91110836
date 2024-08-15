package com.hung.ming.repo.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductId implements java.io.Serializable {
  @Id
  @Column(name = "ORDER_ID")
  private String orderId;

  @Id
  @Column(name = "PRODUCT_ID")
  private String productId;
}
