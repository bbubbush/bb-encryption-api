package com.bb.encryption.api;

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
    return ApiResponse.of(decryptService.decodeAes(param));
  }
}
