package com.bb.encryption.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptService {
  @Value("${aes.private-key}")
  private String secretKey;

  public String encodeAes(String planeText) {
    String encryptText = "";
    try {
      SecretKeySpec key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");

      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encrypted = cipher.doFinal(planeText.getBytes("UTF-8"));
      encryptText = DatatypeConverter.printBase64Binary(encrypted);
    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeException("암호화 중 오류가 발생했습니다.");
    }
    return encryptText;
  }

  public String encodingSha512(String makeCheckSum) {
    MessageDigest md = null;
    String cryptMakeCheckSum = "";
    try {
      md = MessageDigest.getInstance("SHA-512");
      md.update(makeCheckSum.getBytes("UTF-8"));
      cryptMakeCheckSum = String.format("%128x", new BigInteger(1, md.digest()));
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      throw new RuntimeException("검증값 확인 중 오류가 발생했습니다.");
    }
    return cryptMakeCheckSum;
  }
}
