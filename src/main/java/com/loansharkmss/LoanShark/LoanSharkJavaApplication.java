package com.loansharkmss.LoanShark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanSharkJavaApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(LoanSharkJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		
	}
}
