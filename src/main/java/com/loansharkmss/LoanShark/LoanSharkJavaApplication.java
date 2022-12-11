package com.loansharkmss.LoanShark;

import com.loansharkmss.LoanShark.v1.scripts.InstantiateDefaultValues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class LoanSharkJavaApplication {

	private final InstantiateDefaultValues instantiateDefaultValues;

	public LoanSharkJavaApplication (InstantiateDefaultValues instantiateDefaultValues) {
		this.instantiateDefaultValues = instantiateDefaultValues;
	}

	public static void main(String[] args) {
		SpringApplication.run(LoanSharkJavaApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void executeAtStartup() {
		instantiateDefaultValues.instantiateDefaultValuesIfNotSet();
	}

}
