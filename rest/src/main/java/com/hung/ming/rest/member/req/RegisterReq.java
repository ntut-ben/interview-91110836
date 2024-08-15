package com.hung.ming.rest.member.req;

import lombok.Data;

@Data
public class RegisterReq {
  private String username;

  private String paxx;

  private String email;

  private String phoneNumber;
}
