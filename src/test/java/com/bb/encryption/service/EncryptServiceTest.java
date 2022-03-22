package com.bb.encryption.service;

import com.bb.encryption.dto.res.DecryptAesReqDto;
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
    final String planeText = "bbubbush";

    // when
    String encodingText = encryptService.encodeAes(planeText);

    // then
    System.out.println("encodingText :: " + encodingText);
    assertNotNull(encodingText);
    assertNotEquals(planeText, encodingText);
  }
}