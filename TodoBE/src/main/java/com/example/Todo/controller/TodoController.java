package com.example.Todo.controller;

import com.example.Todo.dto.ResponseDTO;
import com.example.Todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {
  @Autowired  // 자동으로 빈을 찾아 인스턴스 멤버변수에 연결
  private TodoService service;

  @GetMapping("test")
  public ResponseEntity<?> testTodo() {
    String str = service.testService();

    List<String> list = new ArrayList<>();
    list.add(str);

    ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.ok().body(res);
  }
}