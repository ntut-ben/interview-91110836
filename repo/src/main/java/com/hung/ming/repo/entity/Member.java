package com.hung.ming.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {
  @Id
  @Column(name = "ID")
  private String id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PAXX")
  private String paxx;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "FULL_NAME")
  private String fullName;

  @Column(name = "DATE_OF_BIRTH")
  private Date dateOfBirth;

  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;

  @Column(name = "CREATED_TIME")
  private Timestamp createdTime;

  @Column(name = "UPDATE_TIME")
  private Timestamp updateTime;

  @Column(name = "STATUS")
  private String status;

}
