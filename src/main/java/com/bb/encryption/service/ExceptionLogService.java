package com.bb.encryption.service;

import com.bb.encryption.entity.ExceptionLog;
import com.bb.encryption.repository.ExceptionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExceptionLogService {
  private final ExceptionLogRepository repository;

  public Long insertLogMessage(String msg) {
    ExceptionLog exceptionInfo = ExceptionLog.builder().msg(msg).build();
    return repository.save(exceptionInfo).getId();
  }
}
