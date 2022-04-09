package com.bb.encryption.service;

import com.bb.encryption.entity.EncryptDataLog;
import com.bb.encryption.repository.EncryptDataLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EncryptDataLogService {
  private final EncryptDataLogRepository repository;

  public Long insertEncryptData(EncryptDataLog encryptDataLog) {
    return repository.save(encryptDataLog).getId();
  }
}
