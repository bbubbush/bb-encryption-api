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
  @NotBlank
  private String planeText;
  @NotNull
  private String type;
}
