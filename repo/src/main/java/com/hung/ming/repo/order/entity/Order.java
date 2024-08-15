package com.hung.ming.repo.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "ORDER_TABLE")
public class Order {
  @Id
  @Column(name = "ID")
  private String id;

  @Column(name = "MEMBER_ID")
  private String memberId;

  @Column(name = "ORDER_DATE")
  private Timestamp orderDate;

  @Column(name = "TOTAL_AMOUNT")
  private BigDecimal totalAmount;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "CREATED_TIME")
  private Timestamp createdTime;

  @Column(name = "UPDATE_TIME")
  private Timestamp updateTime;

}
