package com.bb.encryption.api;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.type.AesType;
import com.bb.encryption.vo.req.DecryptAesReqVO;
import com.bb.encryption.util.ApiResponse;
import com.bb.encryption.vo.common.ResponseVO;
import com.bb.encryption.service.DecryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dec")
@RequiredArgsConstructor
public class DecryptionController {
  private final DecryptService decryptService;

  @PostMapping("/aes")
  public ResponseVO<String> decryptAes(@RequestBody @Valid DecryptAesReqVO param) {
    if (param.getType().equals(AesType.EMPTY)) {
      return ApiResponse.fail(ResponseCode.INVALID_MODE_ERROR);
    }
    return ApiResponse.of(decryptService.decodeAes(param));
  }
}
