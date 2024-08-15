package com.hung.ming.rest.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class HelloWorldCtrl {

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "Hello World";
  }
}
