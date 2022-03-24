package com.bb.encryption.api;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.type.AesType;
import com.bb.encryption.vo.req.EncryptAesReqVO;
import com.bb.encryption.vo.req.EncryptShaReqVO;
import com.bb.encryption.util.ApiResponse;
import com.bb.encryption.vo.common.ResponseVO;
import com.bb.encryption.service.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/enc")
@RequiredArgsConstructor
public class EncryptionController {
  private final EncryptService encryptService;

  @PostMapping("/aes")
  public ResponseVO<String> encryptAes(@RequestBody @Valid EncryptAesReqVO param) {
    if (param.getType().equals(AesType.EMPTY)) {
      return ApiResponse.fail(ResponseCode.INVALID_MODE_ERROR);
    }
    return ApiResponse.of(encryptService.encodeAes(param));
  }
  @PostMapping("/sha/512")
  public ResponseVO<String> encryptSha512(@RequestBody @Valid EncryptShaReqVO param) {
    return ApiResponse.of(encryptService.encodeSha512(param));
  }
}
