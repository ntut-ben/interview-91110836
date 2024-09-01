package com.hung.ming.rest.order.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderReq {
  @Schema(description = "訂單編號", example = "770e8400-e29b-41d4-a716-446655440000",
      requiredProperties = {"orderId"})
  private String orderId;

  @Schema(description = "商品編號", example = "660e8400-e29b-41d4-a716-446655440001",
      requiredProperties = {"productId"})
  private String productId;

  @Schema(description = "數量", example = "3")
  private int quantity;

  @Schema(description = "會員編號", example = "550e8400-e29b-41d4-a716-446655440000",
      requiredProperties = {"id"})
  private String memberId;
}
