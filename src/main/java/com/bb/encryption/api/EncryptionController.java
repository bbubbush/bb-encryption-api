package com.bb.encryption.api;

import com.bb.encryption.dto.req.EncryptAesReqDto;
import com.bb.encryption.model.common.ApiResponse;
import com.bb.encryption.model.common.ResponseModel;
import com.bb.encryption.service.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/aes")
@RequiredArgsConstructor
public class EncryptionController {
  private final EncryptService encryptService;

  @PostMapping("/enc")
  public ResponseModel encryptAes(@RequestBody @Valid EncryptAesReqDto param) {
    return ApiResponse.of(encryptService.encodeAes(param));
  }
}
