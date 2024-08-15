package com.hung.ming.rest.order.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GetMemberOrderStatisticsReq {
  @Schema(description = "會員訂單數量", example = "0")
  private int count;
}
