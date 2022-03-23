package com.bb.encryption.dto.req;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecryptAesReqDto {
  @NotBlank
  private String encodingText;
  @NotBlank
  private String secretKey;
}
