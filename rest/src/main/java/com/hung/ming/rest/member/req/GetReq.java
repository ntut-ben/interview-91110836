package com.hung.ming.rest.member.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class GetReq {
  @Schema(description = "會員編號", example = "550e8400-e29b-41d4-a716-446655440000",
      requiredProperties = {"id"})
  private String id;
}
