package com.bb.encryption.vo.common;

import com.bb.encryption.code.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseVO<T> {
  private String resultCode;
  private String resultMessage;
  private T body;

  public static <T> ResponseVO<T> success(T body) {
    ResponseVO responseModel = new ResponseVO();
    responseModel.setResultCode(ResponseCode.SUCCESS.getCode());
    responseModel.setResultMessage(ResponseCode.SUCCESS.getMessage());
    responseModel.setBody(body);
    return responseModel;
  }
  public static <T> ResponseVO<T> fail(ResponseCode code) {
    ResponseVO responseModel = new ResponseVO();
    responseModel.setResultCode(code.getCode());
    responseModel.setResultMessage(code.getMessage());
    responseModel.setBody(null);
    return responseModel;
  }
}
