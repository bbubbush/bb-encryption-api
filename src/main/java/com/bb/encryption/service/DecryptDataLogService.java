package com.bb.encryption.service;

import com.bb.encryption.entity.DecryptDataLog;
import com.bb.encryption.repository.DecryptDataLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DecryptDataLogService {
  private final DecryptDataLogRepository repository;

  public Long insertDecryptData(DecryptDataLog decryptDataLog) {
    return repository.save(decryptDataLog).getId();
  }
}
