package com.corp.indexcalculationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corp.indexcalculationservice.entity.InstrumentIndex;

public interface InstrumentIndexRepository extends JpaRepository<InstrumentIndex, Long> {
	List<InstrumentIndex> findByInstrument(String instrument);
}
