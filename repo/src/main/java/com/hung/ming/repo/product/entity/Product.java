package com.hung.ming.repo.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {
  @Id
  @Column(name = "ID")
  private String id;

  @Column(name = "NAME")
  private String name;

  @Lob
  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "STOCK")
  private Integer stock;

  @Column(name = "CREATED_TIME")
  private Timestamp createdTime;

  @Column(name = "UPDATE_TIME")
  private Timestamp updateTime;

}
