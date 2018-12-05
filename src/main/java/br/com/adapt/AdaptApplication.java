package br.com.adapt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.adapt.framework.service.SchedulerService;

@SpringBootApplication
@EnableScheduling
public class AdaptApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdaptApplication.class, args);		
	}
}
