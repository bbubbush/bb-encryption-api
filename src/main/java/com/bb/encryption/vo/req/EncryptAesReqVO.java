package com.bb.encryption.vo.req;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncryptAesReqVO {
  @NotBlank
  private String planeText;
  @NotBlank
  private String secretKey;
}
