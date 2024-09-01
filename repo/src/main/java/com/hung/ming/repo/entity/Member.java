package com.hung.ming.repo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Member implements Serializable {
  @Serial
  private static final long serialVersionUID = -8626791827557454104L;

  private String id;

  private String username;

  private String paxx;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;

  private Timestamp createdTime;

  private Timestamp updateTime;

  private String status;
}
