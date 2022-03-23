package com.bb.encryption.dto.req;

import com.bb.encryption.type.EncryptType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EncryptAesReqDto {
  @NotBlank
  private String planeText;

  private EncryptType encryptType;
}