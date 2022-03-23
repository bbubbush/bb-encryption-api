package com.bb.encryption.config;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.exception.DecryptException;
import com.bb.encryption.exception.EncryptException;
import com.bb.encryption.model.common.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
  private final String INVALID_KEY_MESSAGE = "java.security.InvalidKeyException";
  private final String BAD_KEY_MESSAGE = "javax.crypto.BadPaddingException";

  @ExceptionHandler(EncryptException.class)
  public <T> ResponseModel<T> encryptExceptionHandler(Exception e) {
    if (e.getMessage().contains(INVALID_KEY_MESSAGE)) {
      return ResponseModel.fail(ResponseCode.KEY_LENGTH_ERROR);
    }
    return ResponseModel.fail(ResponseCode.ENCRYPTION_ERROR);
  }

  @ExceptionHandler(DecryptException.class)
  public <T> ResponseModel<T> decryptExceptionHandler(Exception e) {
    log.debug(e.getMessage());
    if (e.getMessage().contains(INVALID_KEY_MESSAGE)) {
      return ResponseModel.fail(ResponseCode.KEY_LENGTH_ERROR);
    } else if (e.getMessage().contains(BAD_KEY_MESSAGE)) {
      return ResponseModel.fail(ResponseCode.BAD_KEY_ERROR);
    }
    return ResponseModel.fail(ResponseCode.DECRYPTION_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public <T> ResponseModel<T> exceptionHandler() {
    return ResponseModel.fail(ResponseCode.SERVER_ERROR);
  }
}
