package com.hung.ming.repo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderDto {
  private String id;

  private String memberId;

  private Timestamp orderDate;

  private BigDecimal totalAmount;

  private String status;

  private Timestamp createdTime;
}
