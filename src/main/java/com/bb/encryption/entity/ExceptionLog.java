package com.bb.encryption.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExceptionLog extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String msg;

  @Builder
  public ExceptionLog(Long id, String msg) {
    this.id = id;
    this.msg = msg;
  }
}
