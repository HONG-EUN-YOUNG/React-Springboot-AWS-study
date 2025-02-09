package com.example.Todo.dto;

import com.example.Todo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
  // userId 없음 -> 스프링 시큐리티로 인증 구현
  private String id;
  private String title;
  private boolean done;

  public TodoDTO(final TodoEntity entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.done = entity.isDone();
  }

  // DTO -> Entity
  public static TodoEntity toEntity(final TodoDTO dto) {
    return TodoEntity.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .done(dto.isDone())
        .build();
  }
}