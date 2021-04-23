package io.wander.coronahelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import io.wander.coronahelper.data.Greeting;
import io.wander.coronahelper.dao.*;


@SpringBootApplication // (scanBasePackageClasses = "io.wander.*"})
@EnableAutoConfiguration
@EnableJpaRepositories("io.wander")
@EntityScan(basePackages="io.wander")
//@ComponentScan(basePackages = {"io.wander.coronahelper.data","io.wander.coronahelper.restControllers"})
public class CoronaHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaHelperApplication.class, args);
		System.out.println("First Hello... ");
	}

	
	
	@Component
	class DefaultLoginUserDBEntryCommandLineRunner implements CommandLineRunner {
		@Override
		public void run(String... args) throws Exception {
			for (Greeting g : this.greetings.findAll()) {
				System.out.println(g);
			}
			
		}
		
		@Autowired GreetingsRepository greetings;
	}
	
	
	
}
