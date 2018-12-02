package br.com.adapt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adapt.framework.service.SchedulerService;

@SpringBootApplication
public class AdaptApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdaptApplication.class, args);		
	}
}
