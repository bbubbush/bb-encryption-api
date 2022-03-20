package com.bb.encryption.service;

import com.bb.encryption.dto.res.DecryptAesReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class DecryptServiceTest {
  @Autowired
  private DecryptService decryptService;

  @Test
  public void decryptCase01() {
    // given
    final String expectedText = "bbubbush";
    DecryptAesReqDto param = DecryptAesReqDto
      .builder()
      .encodingText("+7LQejMEkauHPHRrZQ9uKA==\n")
      .build();

    // when
    String decodingText = decryptService.decodeAes(param.getEncodingText());

    // then
    assertEquals(expectedText, decodingText);


  }
}