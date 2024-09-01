package com.hung.ming.repo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderProduct implements Serializable {
  @Serial
  private static final long serialVersionUID = -1865433873588032423L;

  private OrderProductId id;

  private Integer quantity;

  private BigDecimal price;
}
