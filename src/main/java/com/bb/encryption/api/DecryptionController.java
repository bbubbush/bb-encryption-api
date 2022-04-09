package com.bb.encryption.api;

import com.bb.encryption.code.ResponseCode;
import com.bb.encryption.entity.DecryptDataLog;
import com.bb.encryption.service.DecryptDataLogService;
import com.bb.encryption.service.DecryptService;
import com.bb.encryption.type.AesType;
import com.bb.encryption.util.ApiResponse;
import com.bb.encryption.vo.common.ResponseVO;
import com.bb.encryption.vo.req.DecryptAesReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dec")
@RequiredArgsConstructor
@CrossOrigin("*")
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
