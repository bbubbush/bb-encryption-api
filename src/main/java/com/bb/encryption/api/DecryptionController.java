package com.bb.encryption.api;

import com.bb.encryption.dto.req.DecryptAesReqDto;
import com.bb.encryption.model.common.ApiResponse;
import com.bb.encryption.model.common.ResponseModel;
import com.bb.encryption.service.DecryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/aes")
@RequiredArgsConstructor
public class DecryptionController {
  private final DecryptService decryptService;

  @PostMapping("/dec")
  public ResponseModel decryptAes(@RequestBody @Valid DecryptAesReqDto param) {
    return ApiResponse.of(decryptService.decodeAes(param));
  }
}
