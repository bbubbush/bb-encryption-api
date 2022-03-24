package com.bb.encryption.vo.req;

import com.bb.encryption.type.AesType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

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
  @NotNull
  private AesType type;

  public void setType(String type) {
    this.type = Arrays.stream(AesType.values())
      .filter(t -> t.getType().equals(type))
      .findFirst()
      .orElse(AesType.EMPTY)
      ;
  }
}
