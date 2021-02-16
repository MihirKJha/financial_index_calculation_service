package com.corp.indexcalculationservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TicksStatisticsResponse {
	private double avg;
	private double max;
	private double min;
	private int count;
}