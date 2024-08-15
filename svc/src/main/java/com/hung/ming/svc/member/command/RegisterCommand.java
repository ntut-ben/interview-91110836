package com.hung.ming.svc.member.command;

import lombok.Data;

@Data
public class RegisterCommand {
  private String username;

  private String paxx;

  private String email;

  private String phoneNumber;

  private String fullName;
}
