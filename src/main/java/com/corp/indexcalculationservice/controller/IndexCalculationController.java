package com.corp.indexcalculationservice.controller;

import java.time.Instant;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corp.indexcalculationservice.dto.AddTicksRequest;
import com.corp.indexcalculationservice.dto.TicksStatisticsResponse;
import com.corp.indexcalculationservice.service.IndexCalculationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("Rest controller for index calculation")
@RestController
@RequestMapping(
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class IndexCalculationController {

	@Autowired
	private IndexCalculationService indexService;

	@ApiOperation("API to add new ticks")
	@PostMapping("/ticks")
	public void addTicks(@RequestBody @Validated AddTicksRequest addTicksRequest,HttpServletResponse response) {
		 Instant instant = Instant.now();
		 long currentTimeInMillis = instant.toEpochMilli();
		 long ticksTimeInMillis = addTicksRequest.getTimestamp();
		
		 log.info("currentTimeInMillis {}" ,currentTimeInMillis);
		 
		 log.info("ticksTimeInMillis {}" ,ticksTimeInMillis);
		 
		 if((currentTimeInMillis-ticksTimeInMillis) > 60000) {
			 response.setStatus(HttpStatus.NO_CONTENT.value());
			 return;
		 }
		 
		 indexService.addTicks(addTicksRequest);
			
	}

	@ApiOperation("API to calculate statistics for all instruments")
	@GetMapping("/statistics")
	public TicksStatisticsResponse calculateStatistics() {
		return indexService.getStatistics();
	}
	
	@ApiOperation("API to calculate statistics for given instrument")
	@GetMapping("/statistics/{instrumentCode}")
	public TicksStatisticsResponse calculateStatistics(@PathVariable @Validated String instrumentCode) {
		return indexService.getStatistics(instrumentCode);
	}
}