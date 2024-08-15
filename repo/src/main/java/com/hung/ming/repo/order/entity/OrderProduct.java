package com.hung.ming.repo.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {
  @Id
  @Column(name = "ORDER_ID")
  private String orderId;

  @Id
  @Column(name = "PRODUCT_ID")
  private String productId;

  @Column(name = "QUANTITY")
  private Integer quantity;

  @Column(name = "PRICE")
  private BigDecimal price;

}
