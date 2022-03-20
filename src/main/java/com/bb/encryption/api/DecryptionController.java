package com.bb.encryption.api;

import com.bb.encryption.dto.res.DecryptAesReqDto;
import com.bb.encryption.service.DecryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/dec")
@RequiredArgsConstructor
public class DecryptionController {
  private final DecryptService decryptService;

  @GetMapping("/aes")
  public ResponseEntity<String> decryptAes(@Valid DecryptAesReqDto param) {
    return ResponseEntity.ok(decryptService.decodeAes(param.getEncodingText()));
  }
}
