package com.bb.encryption.service;

import com.bb.encryption.entity.DecryptDataLog;
import com.bb.encryption.exception.DecryptException;
import com.bb.encryption.type.AesType;
import com.bb.encryption.vo.req.DecryptAesReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;

@Service
@RequiredArgsConstructor
@Transactional
public class DecryptService {
  private final DecryptDataLogService decryptDataLogService;

  public String decodeAes(DecryptAesReqVO param) {
    String decodingText;
    String encodingText = param.getEncodingText();
    String secretKey = param.getSecretKey();
    try {
      Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
      Cipher cipher = Cipher.getInstance(param.getType().getValue());

      // 암호화 타입에 맞게 분리
      if (AesType.CBC.equals(param.getType())) {
        String iv = secretKey.substring(0, 16);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
      } else if (AesType.ECB.equals(param.getType())) {
        cipher.init(Cipher.DECRYPT_MODE, key);
      }

      byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(encodingText);
      byte[] decrypted = cipher.doFinal(parseBase64Binary);

      decodingText = new String(decrypted, StandardCharsets.UTF_8);
    } catch (GeneralSecurityException e) {
      throw new DecryptException(e);
    }

    DecryptDataLog decryptDataLog = DecryptDataLog.builder()
      .decrytType(param.getType().getValue())
      .secretKey(param.getSecretKey())
      .encodingText(param.getEncodingText())
      .decodingText(decodingText)
      .build();
    this.insertSuccessData(decryptDataLog);

    return decodingText;
  }

  private Long insertSuccessData(DecryptDataLog decryptDataLog) {
    return decryptDataLogService.insertDecryptData(decryptDataLog);
  }
}
