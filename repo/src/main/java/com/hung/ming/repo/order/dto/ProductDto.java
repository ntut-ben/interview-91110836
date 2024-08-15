package com.hung.ming.repo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private String orderId;

  private String name;

  private String description;

  private int quantity;
}
