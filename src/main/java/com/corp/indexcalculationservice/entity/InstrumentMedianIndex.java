package com.corp.indexcalculationservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INSTRUMENT_MEDIAN_INDEX")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentMedianIndex {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;   

	@Column(name = "AVG")
	private double avg;

	@Column(name = "MAX")
	private double max;

	@Column(name = "MIN")
	private double min;
	
	@Column(name = "COUNT")
	private int count;
	
	@Column(name = "CREATED_ON")
	private Date createdOn;
}