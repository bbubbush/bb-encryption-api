package com.bb.encryption.exception;

public class EncryptException extends RuntimeException {
  public EncryptException(Exception e) {
    super(e);
  }
}
