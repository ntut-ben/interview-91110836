package com.hung.ming.svc.order.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderBean {
  private String id;

  private String memberId;

  private Timestamp orderDate;

  private BigDecimal totalAmount;

  private String status;

  private Timestamp createdTime;

  List<ProductBean> products;
}
