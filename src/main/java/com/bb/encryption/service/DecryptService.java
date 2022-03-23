package com.bb.encryption.service;

import com.bb.encryption.dto.req.DecryptAesReqDto;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;

@Service
public class DecryptService {

  public String decodeAes(DecryptAesReqDto param) {
    return this.decodeAes(param.getEncodingText(), param.getSecretKey());
  }

  public String decodeAes(String encodingText, String secretKey) {
    String decodingText = "";
    try {
      Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
      String iv = secretKey.substring(0, 16);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

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
