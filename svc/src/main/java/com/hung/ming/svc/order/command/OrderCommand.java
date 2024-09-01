package com.hung.ming.svc.order.command;

import lombok.Data;

@Data
public class OrderCommand {
  private String orderId;

  private String productId;

  private int quantity;

  private String memberId;
}
