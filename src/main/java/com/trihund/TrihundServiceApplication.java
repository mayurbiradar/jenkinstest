package com.trihund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.trihund.dao.TrihundDaoImpl;
import com.trihund.service.TrihundServiceImpl;

@SpringBootApplication
public class TrihundServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrihundServiceApplication.class, args);
	}
	
	@Bean
	public TrihundServiceImpl getBean()
	{
		return new TrihundServiceImpl();
	}
	
	@Bean
	public TrihundDaoImpl getBean2()
	{
		return new TrihundDaoImpl();
	}

}

