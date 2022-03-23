package com.bb.encryption.code;

public enum ResponseCode {
  SUCCESS("200", "")
  , KEY_LENGTH_ERROR("501", "입력하신 비밀키의 길이를 확인하시기 바랍니다.")
  , ENCRYPTION_ERROR("502", "암호화 중 오류가 발생했습니다.")
  , DECRYPTION_ERROR("503", "복호화 중 오류가 발생했습니다.")
  , BAD_KEY_ERROR("504", "복호화 중 오류가 발생했습니다. 입력하신 비밀키를 확인하시기 바랍니다.")
  ;
  private String code;
  private String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return this.code;
  }

  public String getMessage() {
    return message;
  }
}
