package com.bb.encryption.vo.req;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncryptBCryptReqVO {
  @NotBlank(message = "planeText 값은 필수입니다.")
  private String planeText;
  @Min(value = 4, message = "strength 값은 4 이상이여야 합니다.")
  @Max(value = 12, message = "strength 값은 12 이하여야 합니다.")
  private int strength;
}

