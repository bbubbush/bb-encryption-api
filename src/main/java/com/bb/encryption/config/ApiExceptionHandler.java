package com.bb.encryption.config;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.exception.DecryptException;
import com.bb.encryption.exception.EncryptException;
import com.bb.encryption.service.ExceptionLogService;
import com.bb.encryption.vo.common.ResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApiExceptionHandler {
  private final String INVALID_KEY_MESSAGE = "java.security.InvalidKeyException";
  private final String BAD_KEY_MESSAGE = "javax.crypto.BadPaddingException";
  private final String INVALID_TYPE_MESSAGE = "InvalidEncryptTypeException";
  private final ExceptionLogService exceptionLogService;

  @ExceptionHandler(EncryptException.class)
  public <T> ResponseVO<T> encryptExceptionHandler(EncryptException e) {
    if (e.getMessage().contains(INVALID_KEY_MESSAGE)) {
      return insertExceptionLog(ResponseCode.KEY_LENGTH_ERROR);
    } else if (e.getMessage().contains(INVALID_TYPE_MESSAGE)) {
      return insertExceptionLog(ResponseCode.INVALID_MODE_ERROR);
    }
    return insertExceptionLog(ResponseCode.ENCRYPTION_ERROR);
  }

  @ExceptionHandler(DecryptException.class)
  public <T> ResponseVO<T> decryptExceptionHandler(DecryptException e) {
    if (e.getMessage().contains(INVALID_KEY_MESSAGE)) {
      return insertExceptionLog(ResponseCode.KEY_LENGTH_ERROR);
    } else if (e.getMessage().contains(BAD_KEY_MESSAGE)) {
      return insertExceptionLog(ResponseCode.BAD_KEY_ERROR);
    }
    return insertExceptionLog(ResponseCode.DECRYPTION_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public <T> ResponseVO<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    String filedName = e.getBindingResult()
      .getAllErrors()
      .get(0)
      .getObjectName();

    String errorMsg = ResponseCode.NOT_VALID_ERROR.getMessage() + " [" + filedName + "]";
    exceptionLogService.insertLogMessage(errorMsg);
    return ResponseVO.fail(ResponseCode.NOT_VALID_ERROR.getCode(), errorMsg);
  }

  @ExceptionHandler(Exception.class)
  public <T> ResponseVO<T> exceptionHandler(Exception e) {
    return insertExceptionLog(ResponseCode.SERVER_ERROR);
  }

  private <T> ResponseVO<T> insertExceptionLog(ResponseCode responseCode) {
    exceptionLogService.insertLogMessage(responseCode.getMessage());
    return ResponseVO.fail(responseCode);
  }
}
