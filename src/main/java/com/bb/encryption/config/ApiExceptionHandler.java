package com.bb.encryption.config;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.exception.DecryptException;
import com.bb.encryption.exception.EncryptException;
import com.bb.encryption.vo.common.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
  private final String INVALID_KEY_MESSAGE = "java.security.InvalidKeyException";
  private final String BAD_KEY_MESSAGE = "javax.crypto.BadPaddingException";
  private final String INVALID_TYPE_MESSAGE = "InvalidEncryptTypeException";
  private final String NO_SUCH_ALGORITHM_MESSAGE = "java.security.NoSuchAlgorithmException";

  @ExceptionHandler(EncryptException.class)
  public <T> ResponseVO<T> encryptExceptionHandler(EncryptException e) {
    e.printStackTrace();
    if (e.getMessage().contains(INVALID_KEY_MESSAGE)) {
      return ResponseVO.fail(ResponseCode.KEY_LENGTH_ERROR);
    } else if (e.getMessage().contains(INVALID_TYPE_MESSAGE)) {
      return ResponseVO.fail(ResponseCode.INVALID_MODE_ERROR);
    } else if (e.getMessage().contains(NO_SUCH_ALGORITHM_MESSAGE)) {
      return ResponseVO.fail(ResponseCode.NO_SUCH_ALGORITHM_ERROR);
    }
    return ResponseVO.fail(ResponseCode.ENCRYPTION_ERROR);
  }

  @ExceptionHandler(DecryptException.class)
  public <T> ResponseVO<T> decryptExceptionHandler(DecryptException e) {
    e.printStackTrace();
    if (e.getMessage().contains(INVALID_KEY_MESSAGE)) {
      return ResponseVO.fail(ResponseCode.KEY_LENGTH_ERROR);
    } else if (e.getMessage().contains(BAD_KEY_MESSAGE)) {
      return ResponseVO.fail(ResponseCode.BAD_KEY_ERROR);
    }
    return ResponseVO.fail(ResponseCode.DECRYPTION_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public <T> ResponseVO<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    String filedName = e.getBindingResult()
      .getAllErrors()
      .get(0)
      .getObjectName();
    return ResponseVO.fail(ResponseCode.NOT_VALID_ERROR.getCode(), ResponseCode.NOT_VALID_ERROR.getMessage() + " [" + filedName + "]");
  }

  @ExceptionHandler(Exception.class)
  public <T> ResponseVO<T> exceptionHandler(Exception e) {
    e.printStackTrace();
    return ResponseVO.fail(ResponseCode.SERVER_ERROR);
  }
}
