package com.bb.encryption.service;

import com.bb.encryption.exception.EncryptException;
import com.bb.encryption.type.AesType;
import com.bb.encryption.vo.req.EncryptAesReqVO;
import com.bb.encryption.vo.req.EncryptShaReqVO;
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
    String planeText = param.getPlaneText();
    String secretKey = param.getSecretKey();
    String encryptText;
    try {
      Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
      Cipher cipher = Cipher.getInstance(param.getType().getValue());

      // 암호화 타입에 맞게 분리
      if (AesType.CBC.equals(param.getType())) {
        String iv = secretKey.substring(0, 16);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
      } else if (AesType.ECB.equals(param.getType())) {
        cipher.init(Cipher.ENCRYPT_MODE, key);
      }
      byte[] encrypted = cipher.doFinal(planeText.getBytes(StandardCharsets.UTF_8));
      encryptText = DatatypeConverter.printBase64Binary(encrypted);
    } catch (GeneralSecurityException e) {
      throw new EncryptException(e);
    }
    return encryptText;
  }

//  public String encodeSha512(EncryptShaReqVO param) {
//    String planeText = param.getPlaneText();
//    String encryptText;
//    try {
//      MessageDigest md = MessageDigest.getInstance("SHA-512");
//      md.update(planeText.getBytes(StandardCharsets.UTF_8));
//      encryptText = String.format("%128x", new BigInteger(1, md.digest()));
//    } catch (NoSuchAlgorithmException e) {
//      throw new EncryptException(e);
//    }
//    return encryptText;
//  }
  public String encodeSha(EncryptShaReqVO param) {
    String planeText = param.getPlaneText();
    String encryptText = "";
    String shaType = param.getType();
    try {
      MessageDigest md = MessageDigest.getInstance(shaType);
      md.update(planeText.getBytes(StandardCharsets.UTF_8));
//      if(shaType.equals("SHA-512")) {
//        encryptText = String.format("%128x", new BigInteger(1, md.digest()));
//      } else {
//        encryptText = String.format("%02x", new BigInteger(1, md.digest()));
//      }
      encryptText = String.format("%128x", new BigInteger(1, md.digest())).trim();
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptException(e);
    }
    return encryptText;
  }

}
