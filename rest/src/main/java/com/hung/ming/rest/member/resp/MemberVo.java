package com.hung.ming.rest.member.resp;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberVo {
  private String id;

  private String username;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;
}
