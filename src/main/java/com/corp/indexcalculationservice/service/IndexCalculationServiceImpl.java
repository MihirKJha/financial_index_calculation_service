package com.corp.indexcalculationservice.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corp.indexcalculationservice.dto.AddTicksRequest;
import com.corp.indexcalculationservice.dto.TicksStatisticsResponse;
import com.corp.indexcalculationservice.entity.InstrumentIndex;
import com.corp.indexcalculationservice.entity.InstrumentMedianIndex;
import com.corp.indexcalculationservice.entity.InstrumentTicks;
import com.corp.indexcalculationservice.repository.InstrumentIndexRepository;
import com.corp.indexcalculationservice.repository.InstrumentMedianIndexRepository;
import com.corp.indexcalculationservice.repository.InstrumentTicksRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class IndexCalculationServiceImpl implements IndexCalculationService {

	@Autowired
	private InstrumentTicksRepository instrumentTicksRepository;
	
	@Autowired
	private InstrumentIndexRepository instrumentIndexRepository;
	
	@Autowired
	private InstrumentMedianIndexRepository instrumentMedianIndexRepository;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTicks(AddTicksRequest addTicksRequest) {
		 Instant instant = Instant.now();
		 long currentTimeInMillis = instant.toEpochMilli();
		 
		 InstrumentTicks instrumentTicks = InstrumentTicks.builder().instrument(addTicksRequest.getInstrument())
				 .price(addTicksRequest.getPrice())
				 .timestamp(addTicksRequest.getTimestamp()).build();
		
		 //Save instruments ticks
		 instrumentTicksRepository.save(instrumentTicks);
		 
		List<InstrumentTicks>  ticksInLastSixtySeconds = instrumentTicksRepository.findAll().stream().filter(i -> ((currentTimeInMillis - i.getTimestamp()) <=60000 ))
		      .collect(Collectors.toList());
		
		Double max = ticksInLastSixtySeconds.stream().mapToDouble(i -> i.getPrice()).max().getAsDouble();			
		Double min = ticksInLastSixtySeconds.stream().mapToDouble(i -> i.getPrice()).min().getAsDouble();
		Double avg = ticksInLastSixtySeconds.stream().mapToDouble(i -> i.getPrice()).average().getAsDouble();
		
		InstrumentMedianIndex  medianEntity= InstrumentMedianIndex.builder().avg(avg)
				.count(ticksInLastSixtySeconds.size())
				.max(max).min(min).createdOn(new Date()).build();
		
		//save all instruments details like max,min and avg  
		instrumentMedianIndexRepository.save(medianEntity);
		
		List<InstrumentIndex>  instrumentTicksList = new ArrayList<>();	
		
		Map<String,List<InstrumentTicks>> instrumentMap = instrumentTicksRepository.findAll().stream().filter(i -> ((currentTimeInMillis - i.getTimestamp()) <=60000 ))
	      .collect(Collectors.groupingBy(InstrumentTicks::getInstrument));
		
		for(Map.Entry<String, List<InstrumentTicks>>  instrumentEntry : instrumentMap.entrySet()) {
			String instrumentCode = instrumentEntry.getKey();
			List<InstrumentTicks>  instrumentList = instrumentEntry.getValue();			

			Double instrumentMax = instrumentList.stream().mapToDouble(i -> i.getPrice()).max().getAsDouble();			
			Double instrumentMin = instrumentList.stream().mapToDouble(i -> i.getPrice()).min().getAsDouble();
			Double instumentAvg = instrumentList.stream().mapToDouble(i -> i.getPrice()).average().getAsDouble();
			
			InstrumentIndex  instrumentIndex= InstrumentIndex.builder().avg(instumentAvg)
					.count(instrumentList.size())
					.instrument(instrumentCode)
					.max(instrumentMax).min(instrumentMin).createdOn(new Date()).build();
			
			instrumentTicksList.add(instrumentIndex);			
			
		}
		
		//save instrument index details
		instrumentIndexRepository.saveAll(instrumentTicksList);
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TicksStatisticsResponse getStatistics() {
		List<InstrumentMedianIndex>  instrumentList = instrumentMedianIndexRepository.findAll().stream().sorted(Comparator.comparing(InstrumentMedianIndex::getCreatedOn).reversed())
				.collect(Collectors.toList());
		
		InstrumentMedianIndex index = (instrumentList.size() >0) ? instrumentList.get(0): new InstrumentMedianIndex();
		
		TicksStatisticsResponse medianResponse = TicksStatisticsResponse.builder()
				.min(index.getMin())
				.count(index.getCount())
				.avg(index.getAvg())
				.max(index.getMax())
				.build();
		
		return medianResponse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TicksStatisticsResponse getStatistics(String instrumentCode) {
		List<InstrumentIndex>  instrumentList = instrumentIndexRepository.findByInstrument(instrumentCode)
				.stream().sorted(Comparator.comparing(InstrumentIndex::getCreatedOn).reversed())
				.collect(Collectors.toList());
		
		InstrumentIndex index = (instrumentList.size() >0) ? instrumentList.get(0): new InstrumentIndex();
		
		TicksStatisticsResponse instrumentIndex = TicksStatisticsResponse.builder()
				.min(index.getMin())
				.count(index.getCount())
				.avg(index.getAvg())
				.max(index.getMax())
				.build();
		
		return instrumentIndex;
	}
}