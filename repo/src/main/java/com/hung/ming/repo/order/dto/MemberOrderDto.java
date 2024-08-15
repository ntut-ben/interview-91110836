package com.hung.ming.repo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberOrderDto {
  private String id;

  private String username;

  private String email;

  private String fullName;

  private Date dateOfBirth;

  private String phoneNumber;

  private Long count;
}
