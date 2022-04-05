package com.bb.encryption.service;

import com.bb.encryption.type.AesType;
import com.bb.encryption.vo.req.EncryptAesReqVO;
import com.bb.encryption.vo.req.EncryptShaReqVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class EncryptServiceTest {
  private final String PLANE_TEXT = "안녕하세요 반가워요 잘있어요 다시 만나요";
  @Autowired
  private EncryptService encryptService;


  @Test
  public void encryptAesCase01() {
    // given
    final String secretKey = "bbubbush!@#$%^&*";
    EncryptAesReqVO param = EncryptAesReqVO
      .builder()
      .planeText(this.PLANE_TEXT)
      .secretKey(secretKey)
      .type(AesType.CBC)
      .build();

    // when
    String encodingText = encryptService.encodeAes(param);

    // then
    assertNotNull(encodingText);
    assertNotEquals(this.PLANE_TEXT, encodingText);
  }

  @Test
  public void encryptSha512Case01() {
    // given
    EncryptShaReqVO param = EncryptShaReqVO
      .builder()
      .planeText(this.PLANE_TEXT)
      .type("SHA-512")
      .build();

    // when
    String encodingText = encryptService.encodeSha(param);

    // then
    assertNotNull(encodingText);
    assertNotEquals(this.PLANE_TEXT, encodingText);
  }
}