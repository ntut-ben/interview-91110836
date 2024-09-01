package com.hung.ming.repo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Order implements Serializable {
  @Serial
  private static final long serialVersionUID = -8450127723028480932L;

  private String id;

  private String memberId;

  private Timestamp orderDate;

  private BigDecimal totalAmount;

  private String status;

  private Timestamp createdTime;

  private Timestamp updateTime;

}
