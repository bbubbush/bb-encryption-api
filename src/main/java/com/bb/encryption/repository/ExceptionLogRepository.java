package com.bb.encryption.repository;

import com.bb.encryption.entity.ExceptionLog;
import org.springframework.data.repository.CrudRepository;

public interface ExceptionLogRepository extends CrudRepository<ExceptionLog, Long> {
}
