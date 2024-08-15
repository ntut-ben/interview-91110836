package com.hung.ming.rest.member.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Date;

@Data
public class EditReq {
  @Schema(description = "會員編號", example = "550e8400-e29b-41d4-a716-446655440000")
  private String id;

  @Schema(description = "電子郵件", example = "zxcvb@hotmail.com")
  private String email;

  @Schema(description = "姓名", example = "麵包超人")
  private String fullName;

  @Schema(description = "生日", type = "string", example = "2000-01-01")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateOfBirth;

  @Schema(description = "手機號碼", example = "0919123456")
  private String phoneNumber;
}
