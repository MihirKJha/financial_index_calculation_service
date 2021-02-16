package com.corp.indexcalculationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corp.indexcalculationservice.entity.InstrumentTicks;

public interface InstrumentTicksRepository extends JpaRepository<InstrumentTicks, Long> {	
}
