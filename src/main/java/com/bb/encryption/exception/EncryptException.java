package com.bb.encryption.exception;

import java.security.InvalidKeyException;

public class EncryptException extends RuntimeException {
  public EncryptException(Exception e) {
    super(e);
  }
}
