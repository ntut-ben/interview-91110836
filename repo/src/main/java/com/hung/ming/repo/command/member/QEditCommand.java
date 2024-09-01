package com.hung.ming.repo.command.member;

import lombok.Data;

import java.sql.Date;

@Data
public class QEditCommand {
  private String id;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;
}
