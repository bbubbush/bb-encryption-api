package com.bb.encryption.vo.req;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncryptShaReqVO {
  @NotBlank(message = "planeText 값은 필수입니다.")
  private String planeText;
  @NotNull(message = "type 값은 필수입니다.")
  private String type;
}
