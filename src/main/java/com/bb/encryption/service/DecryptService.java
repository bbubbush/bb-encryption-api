package com.bb.encryption.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

@Service
public class DecryptService {
  @Value("${aes.private-key}")
  private String secretKey;

  public String decodeAes(String encodingText) {
    String decodingText = "";

    try {
      SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");

      Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
      cipher.init(Cipher.DECRYPT_MODE, key);

      byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(encodingText);
      byte[] decrypted = cipher.doFinal(parseBase64Binary);

      decodingText = new String(decrypted);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
      throw new RuntimeException("복호화 중 오류가 발생했습니다.");
    }
    return decodingText;
  }
}
