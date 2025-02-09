package com.example.Todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // 엔티티로 지정
@Table(name = "Todo") // Todo 테이블에 매핑
// Table name이 없으면 Entity name, 이것도 없으면 클래스명을 테이블명으로 간주
public class TodoEntity {
  @Id // 기본키 지정
//  @GeneratedValue(generator="system-uuid")
//  @GenericGenerator(name="system-uuid", strategy = "uuid") -> deprecated
  @UuidGenerator
  private String id;

  private String userId;
  private String title;
  private boolean done;
}