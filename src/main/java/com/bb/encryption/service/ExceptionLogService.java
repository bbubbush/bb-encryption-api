package com.bb.encryption.service;

import com.bb.encryption.entity.ExceptionLog;
import com.bb.encryption.repository.ExceptionLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExceptionLogService {
  private final ExceptionLogRepository repository;

  public Long insertLogMessage(String msg) {
    ExceptionLog exceptionInfo = ExceptionLog.builder().msg(msg).build();
    ExceptionLog save = repository.save(exceptionInfo);
    log.debug("save msg :: " + save.getMsg());
    return save.getId();
  }
}
