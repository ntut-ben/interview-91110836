package com.hung.ming.rest.order.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hung.ming.rest.common.req.PageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetOrderReq extends PageReq {
  @Schema(description = "訂單編號", example = "770e8400-e29b-41d4-a716-446655440000")
  private String orderNo;
  @Schema(description = "產品名稱", example = "產品C")
  private String productName;
  @Schema(description = "訂單建立日期", type = "string", example = "2024-08-07 09:30:00")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp orderCreateDate;
}
