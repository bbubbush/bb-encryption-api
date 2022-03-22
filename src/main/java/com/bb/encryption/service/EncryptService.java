package com.bb.encryption.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptService {
  @Value("${aes.private-key}")
  private String secretKey;

  public String encodeAes(String planeText) {
    String encryptText = "";
    try {
      Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      String iv = secretKey.substring(0, 16);
      cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
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
