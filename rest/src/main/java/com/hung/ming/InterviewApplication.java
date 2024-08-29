package com.hung.ming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hung.ming")
public class InterviewApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterviewApplication.class, args);
  }

}
