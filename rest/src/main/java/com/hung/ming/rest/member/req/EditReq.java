package com.hung.ming.rest.member.req;

import lombok.Data;

import java.sql.Date;

@Data
public class EditReq {
  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;
}
