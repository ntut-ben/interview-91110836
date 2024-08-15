package com.hung.ming.rest.member.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegisterReq {
  @Schema(description = "帳號", example = "ming")
  private String username;

  @Schema(description = "密碼", example = "123456")
  private String paxx;

  @Schema(description = "電子郵件", example = "3345678@yahoo.com.tw")
  private String email;

  @Schema(description = "手機號碼", example = "0912345678")
  private String phoneNumber;

  @Schema(description = "姓名", example = "王小明")
  private String fullName;
}
