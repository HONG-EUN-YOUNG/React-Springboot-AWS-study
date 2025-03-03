package com.example.Todo.controller;

import com.example.Todo.dto.ResponseDTO;
import com.example.Todo.dto.UserDTO;
import com.example.Todo.model.UserEntity;
import com.example.Todo.security.TokenProvider;
import com.example.Todo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private TokenProvider tokenProvider;

  // 회원가입
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
    try {
      if(userDTO == null || userDTO.getPassword() == null ) {
        throw new RuntimeException("Invalid Password value.");
      }

      // 유저 정보 생성
      UserEntity user = UserEntity.builder()
          .username(userDTO.getUsername())
          .password(userDTO.getPassword())
          .build();

      // 유저 저장
      UserEntity registeredUser = userService.create(user);
      UserDTO responseUserDTO = UserDTO.builder()
          .id(registeredUser.getId())
          .username(registeredUser.getUsername())
          .build();

      return ResponseEntity.ok().body(responseUserDTO);
    } catch (Exception e) {
      ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();

      return ResponseEntity
          .badRequest()
          .body(responseDTO);
    }
  }

  // 로그인
  @PostMapping("/signin")
  public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
    UserEntity user = userService.getByCredentials(
        userDTO.getUsername(),
        userDTO.getPassword());

    if(user != null) {
      // 토큰 생성
      final String token = tokenProvider.create(user);

      final UserDTO responseUserDTO = UserDTO.builder()
          .username(user.getUsername())
          .id(user.getId())
          .token(token) // dto에 토큰 반환
          .build();

      return ResponseEntity.ok().body(responseUserDTO);
    } else {
      ResponseDTO responseDTO = ResponseDTO.builder()
          .error("Login failed.")
          .build();

      return ResponseEntity
          .badRequest()
          .body(responseDTO);
    }
  }
}