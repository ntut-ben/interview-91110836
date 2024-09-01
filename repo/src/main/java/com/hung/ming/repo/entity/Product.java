package com.hung.ming.repo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Product implements Serializable {
  @Serial
  private static final long serialVersionUID = -4809919135919377603L;

  private String id;

  private String name;

  private String description;

  private BigDecimal price;

  private Integer stock;

  private Timestamp createdTime;

  private Timestamp updateTime;

}
