package com.bb.encryption.dto.req;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncryptAesReqDto {
  @NotBlank
  private String planeText;
  @NotBlank
  private String secretKey;
}
