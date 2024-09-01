package com.hung.ming.repo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class OrderProductId implements Serializable {
  @Serial
  private static final long serialVersionUID = 5746525234754277858L;

  private String orderId;

  private String productId;
}
