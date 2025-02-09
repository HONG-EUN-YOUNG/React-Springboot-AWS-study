package com.example.Todo.controller;

import com.example.Todo.dto.ResponseDTO;
import com.example.Todo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test") // 리소스
public class TestController {
  @GetMapping
  public String testController() {
    return "Hello World!";
  }

  // 메소드에서 경로 지정
  @GetMapping("/testGetMapping")
  public String testControllerWithPath() {
    return "Hello World! testGetMapping";
  }

  // 매개변수 사용 1
  @GetMapping("/{id}")
  public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
    return "Hello World! ID " + id;
  }

  // 매개변수 사용 2
  @GetMapping("/testRequestParam")
  public String testControllerRequestParam(@RequestParam(required = false) int id) {
    return "Hello World! ID " + id;
  }

  // 복잡한 리소스
  @GetMapping("/testRequestBody")
  public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
    return "Hello World! ID : " + testRequestBodyDTO.getId() +
           ", Message : " + testRequestBodyDTO.getMessage();
  }

  // ResponseDTO 반환
  @GetMapping("/testResponseBody")
  public ResponseDTO<String> testControllerResponseBody() {
    List<String> list = new ArrayList<>();
    list.add("Hello World! I'm ResponseDTO");

    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return response;
  }

  // ResponseEntity 반환 -> HTTP 응답바디 + status, header 등을 조작하고 싶을 때 사용
  @GetMapping("/testResponseEntity")
  public ResponseEntity<?> testControllerResponseEntity() {
    List<String> list = new ArrayList<>();
    list.add("Hello World! I'm ResponseEntity. And you got 400!");

    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    // http status를 400로 설정.
    return ResponseEntity.badRequest().body(response);
  }
}