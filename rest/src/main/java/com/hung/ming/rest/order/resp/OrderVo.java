package com.hung.ming.rest.order.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderVo {
  private String id;

  private String memberId;

  private Timestamp orderDate;

  private BigDecimal totalAmount;

  private String status;

  private Timestamp createdTime;

  List<ProductVo> products;
}
