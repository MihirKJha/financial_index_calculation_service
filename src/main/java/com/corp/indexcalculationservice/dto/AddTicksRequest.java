package com.corp.indexcalculationservice.dto;

import javax.annotation.Nonnull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AddTicksRequest {
	@Nonnull
	private String instrument;
	@Nonnull
	private double price;
	@Nonnull
	private long timestamp;
}