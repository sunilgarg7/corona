package io.wander.coronahelper.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.wander.coronahelper.dao.EmployeeRepository;

@Component
public class EmployeeLoadDBCommandLineRunner {

	  private static final Logger log = LoggerFactory.getLogger(EmployeeLoadDBCommandLineRunner.class);

	  @Bean
	  CommandLineRunner initDatabase(EmployeeRepository repository) {
		  System.out.println("inside the employee insertion");
	    return args -> {
	      log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
	      log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief"))); // Lolz
	    };
	  }
	}