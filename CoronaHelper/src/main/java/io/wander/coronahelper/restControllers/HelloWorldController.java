package io.wander.coronahelper.restControllers;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.wander.coronahelper.dao.GreetingsRepository;
import io.wander.coronahelper.data.Greeting;

@RestController
public class HelloWorldController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	GreetingsRepository greetings;

	@GetMapping("/hello-world")
	@ResponseBody
	public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping("/")
	public Collection<Greeting> getAllGreetings() {
		return this.greetings.findAll();
	}
	
	// below is for the Model Of HATEOAS Application
//	@RequestMapping("/greeting")
//	public HttpEntity<Greeting> greeting(
//		@RequestParam(value = "name", defaultValue = "World") String name) {
//
//		Greeting greeting = new Greeting(0, String.format(template, name));
//		greeting.add(linkTo(methodOn(HelloWorldController.class).greeting(name)).withSelfRel()); // withSelfRel -- it is also mentioned that it will respect proxy settings
//
//		return new ResponseEntity<>(greeting, HttpStatus.OK);
//	}
	
}
