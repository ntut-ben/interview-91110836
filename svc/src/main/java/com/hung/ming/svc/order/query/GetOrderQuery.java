package com.hung.ming.svc.order.query;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GetOrderQuery {
  private String orderNo;
  private String productName;
  private Timestamp orderCreateDate;
}
