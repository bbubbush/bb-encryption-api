package com.bb.encryption.api;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.service.EncryptService;
import com.bb.encryption.type.AesType;
import com.bb.encryption.util.ApiResponse;
import com.bb.encryption.vo.common.ResponseVO;
import com.bb.encryption.vo.req.EncryptAesReqVO;
import com.bb.encryption.vo.req.EncryptBCryptReqVO;
import com.bb.encryption.vo.req.EncryptShaReqVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/enc")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EncryptionController {
  private final EncryptService encryptService;

  @PostMapping("/aes")
  public ResponseVO<String> encryptAes(@RequestBody @Valid EncryptAesReqVO param) {
    if (param.getType().equals(AesType.EMPTY)) {
      return ApiResponse.fail(ResponseCode.INVALID_MODE_ERROR);
    }
    return ApiResponse.of(encryptService.encodeAes(param));
  }

  @PostMapping("/sha")
  public ResponseVO<String> encryptSha(@RequestBody @Valid EncryptShaReqVO param) {
    String type = param.getType();
    if (type.equals("")) {
      return ApiResponse.fail(ResponseCode.INVALID_MODE_ERROR);
    }
    return ApiResponse.of(encryptService.encodeSha(param));
  }

  @PostMapping("/bcrypt")
  public ResponseVO<String> encryptBCrypt(@RequestBody @Valid EncryptBCryptReqVO param) {
    return ApiResponse.of(encryptService.encodeBcrypt(param));
  }
}
