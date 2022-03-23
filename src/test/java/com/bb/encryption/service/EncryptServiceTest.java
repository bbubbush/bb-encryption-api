package com.bb.encryption.service;

import com.bb.encryption.dto.req.EncryptAesReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EncryptServiceTest {
  @Autowired
  private EncryptService encryptService;

  @Test
  public void encryptCase01() {
    // given
    final String expectedText = "안녕하세요 반가워요 잘있어요 다시";
    final String secretKey = "bbubbush!@#$%^&*";
    EncryptAesReqDto param = EncryptAesReqDto
      .builder()
      .planeText(expectedText)
      .secretKey(secretKey)
      .build();

    // when
    String encodingText = encryptService.encodeAes(param);

    // then
    assertNotNull(encodingText);
    assertNotEquals(expectedText, encodingText);
  }
}