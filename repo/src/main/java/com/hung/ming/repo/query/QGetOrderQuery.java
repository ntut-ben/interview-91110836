package com.hung.ming.repo.query;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class QGetOrderQuery {
  private String orderNo;
  private String productName;
  private Timestamp orderCreateDate;
}
