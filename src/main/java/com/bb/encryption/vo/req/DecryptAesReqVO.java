package com.bb.encryption.vo.req;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecryptAesReqVO {
  @NotBlank
  private String encodingText;
  @NotBlank
  private String secretKey;
}
