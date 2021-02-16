package com.corp.indexcalculationservice.service;

import com.corp.indexcalculationservice.dto.AddTicksRequest;
import com.corp.indexcalculationservice.dto.TicksStatisticsResponse;

public interface IndexCalculationService {
    	
	/**
	 * Method to add ticks in application
	 * 
	 * @param AddTicksRequest addTicksRequest
	 * @return
	 */
	public void addTicks(AddTicksRequest addTicksRequest);
	
	/**
	 * Method to get statistics for all instruments 
	 * 
	 * @return TicksStatisticsResponse
	 */
	public TicksStatisticsResponse getStatistics();
	
	
	/**
	 * Method to get statistics for given instrument 
	 * 
	 * @return CurrencyExchangHistoryResponse
	 */
	public TicksStatisticsResponse getStatistics(String instrumentCode);
	
	
}