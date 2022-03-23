package com.bb.encryption.service;

import com.bb.encryption.dto.req.DecryptAesReqDto;
import com.bb.encryption.dto.req.EncryptAesReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class DecryptServiceTest {
  @Autowired
  private EncryptService encryptService;
  @Autowired
  private DecryptService decryptService;

  @Test
  public void decryptCase01() {
    // given
    final String expectedText = "안녕하세요 반가워요 잘있어요 다시";
    final String secretKey = "bbubbush!@#$%^&*";
    EncryptAesReqDto encryptParam = EncryptAesReqDto
      .builder()
      .planeText(expectedText)
      .secretKey(secretKey)
      .build();
    String encodingText = encryptService.encodeAes(encryptParam);

    DecryptAesReqDto decryptParam = DecryptAesReqDto
      .builder()
      .encodingText(encodingText)
      .secretKey(secretKey)
      .build();

    // when
    System.out.println(encryptParam);
    System.out.println(decryptParam);
    String decodingText = decryptService.decodeAes(decryptParam);

    // then
    assertEquals(expectedText, decodingText);
  }
}