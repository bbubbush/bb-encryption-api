package com.bb.encryption.util;

import com.bb.encryption.vo.common.ResponseVO;

public class ApiResponse<T> extends ResponseVO<T> {
  public static <T> ResponseVO<T> of(T body) {
    return ResponseVO.success(body);
  }
}
