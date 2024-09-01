package com.hung.ming.rest.order.resp;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberOrderVo {
  private String id;

  private String username;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;

  private Long count;
}
