package com.bb.encryption.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EncryptDataLog extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String encrytType;
  private String planeText;
  private String secretKey;
  private String encodingText;

  @Builder
  public EncryptDataLog(String encrytType, String planeText, String secretKey, String encodingText) {
    this.encrytType = encrytType;
    this.planeText = planeText;
    this.secretKey = secretKey;
    this.encodingText = encodingText;
  }
}
