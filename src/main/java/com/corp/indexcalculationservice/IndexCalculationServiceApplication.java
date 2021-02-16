package com.corp.indexcalculationservice;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class IndexCalculationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndexCalculationServiceApplication.class, args);
	}
	
	@Bean
	public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(10);
	    executor.setMaxPoolSize(10);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("Ticks-");
	    executor.initialize();
	    return executor;
	}
}
