package com.jaya.demo.repository;

import com.jaya.demo.model.ExchangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRecordRepository extends JpaRepository<ExchangeRecord, Long> {
}
