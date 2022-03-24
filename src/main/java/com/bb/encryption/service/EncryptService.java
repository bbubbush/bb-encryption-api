package com.bb.encryption.service;

import com.bb.encryption.vo.req.EncryptAesReqVO;
import com.bb.encryption.vo.req.EncryptShaReqVO;
import com.bb.encryption.exception.EncryptException;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptService {

  public String encodeAes(EncryptAesReqVO param) {
    return this.encodeAes(param.getPlaneText(), param.getSecretKey());
  }

  private String encodeAes(String planeText, String secretKey) {
    String encryptText;
    try {
      Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      String iv = secretKey.substring(0, 16);
      cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
      byte[] encrypted = cipher.doFinal(planeText.getBytes(StandardCharsets.UTF_8));
      encryptText = DatatypeConverter.printBase64Binary(encrypted);
    } catch (GeneralSecurityException e) {
      throw new EncryptException(e);
    }
    return encryptText;
  }

  public String encodeSha512(EncryptShaReqVO param) {
    String planeText = param.getPlaneText();
    String encryptText;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(planeText.getBytes(StandardCharsets.UTF_8));
      encryptText = String.format("%128x", new BigInteger(1, md.digest()));
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptException(e);
    }
    return encryptText;
  }
}
