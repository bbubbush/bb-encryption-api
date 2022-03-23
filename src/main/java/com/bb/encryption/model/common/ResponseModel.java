package com.bb.encryption.model.common;

import com.bb.encryption.code.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseModel<T> {
  private String resultCode;
  private String resultMessage;
  private T body;

  public static <T> ResponseModel<T> success(T body) {
    ResponseModel responseModel = new ResponseModel();
    responseModel.setResultCode(ResponseCode.SUCCESS.getCode());
    responseModel.setResultMessage(ResponseCode.SUCCESS.getMessage());
    responseModel.setBody(body);
    return responseModel;
  }
  public static <T> ResponseModel<T> fail(ResponseCode code) {
    ResponseModel responseModel = new ResponseModel();
    responseModel.setResultCode(code.getCode());
    responseModel.setResultMessage(code.getMessage());
    responseModel.setBody(null);
    return responseModel;
  }
}
