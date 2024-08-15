package com.hung.ming.svc.member.command;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterCommand {
  private String paxx;

  private String email;

  private String phoneNumber;
}
