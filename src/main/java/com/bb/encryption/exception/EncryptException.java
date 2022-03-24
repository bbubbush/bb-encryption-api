package com.bb.encryption.exception;

public class EncryptException extends RuntimeException {
  public EncryptException() {
    super();
  }

  public EncryptException(Exception e) {
    super(e);
  }
}
