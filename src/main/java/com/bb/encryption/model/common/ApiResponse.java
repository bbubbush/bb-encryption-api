package com.bb.encryption.model.common;

public class ApiResponse<T> extends ResponseModel<T> {
  public static <T> ResponseModel<T> of(T body) {
    return ResponseModel.success(body);
  }
}
