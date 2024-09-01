package com.hung.ming.svc.order.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class MemberOrderBean {
  private String id;

  private String username;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;

  private Long count;
}
