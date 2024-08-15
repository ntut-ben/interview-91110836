package com.hung.ming.svc.member.command;

import lombok.Data;

import java.sql.Date;

@Data
public class EditCommand {
  private String username;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;
}
