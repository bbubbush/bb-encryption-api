package com.bb.encryption.type;

public enum EncryptType {
  AES_128("aes-128")
  , AES_196("aes-196")
  , AES_256("aes-256");

  private String type;
  EncryptType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
