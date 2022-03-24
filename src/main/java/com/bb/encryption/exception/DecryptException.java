package com.bb.encryption.exception;

public class DecryptException extends RuntimeException {
  public DecryptException() {

  }

  public DecryptException(Exception e) {
    super(e);
  }
}
