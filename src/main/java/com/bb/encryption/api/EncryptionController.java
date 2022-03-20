package com.bb.encryption.api;

import com.bb.encryption.dto.req.EncryptAesReqDto;
import com.bb.encryption.service.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/enc")
@RequiredArgsConstructor
public class EncryptionController {
  private final EncryptService encryptService;

  @GetMapping("/aes")
  public ResponseEntity<String> encryptAes(@Valid EncryptAesReqDto param) {
    return ResponseEntity.ok(encryptService.encodeAes(param.getPlaneText()));
  }
}
