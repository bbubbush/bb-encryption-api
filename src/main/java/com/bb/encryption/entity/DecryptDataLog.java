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
public class DecryptDataLog extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String decrytType;
  private String encodingText;
  private String secretKey;
  private String decodingText;

  @Builder
  public DecryptDataLog(String decrytType, String encodingText, String secretKey, String decodingText) {
    this.decrytType = decrytType;
    this.encodingText = encodingText;
    this.secretKey = secretKey;
    this.decodingText = decodingText;
  }
}
