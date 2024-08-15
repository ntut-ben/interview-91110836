package com.hung.ming.repo.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {
  @EmbeddedId
  private OrderProductId id;

  @Column(name = "QUANTITY")
  private Integer quantity;

  @Column(name = "PRICE")
  private BigDecimal price;

}
