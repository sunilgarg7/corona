package io.wander.CoronaHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
@AutoConfigureMockMvc
class CoronaHelperApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired
	private MockMvc mvc;

	private URL base;

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@BeforeAll
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + this.port + "/hello-world", Map.class); // TODO hello-world

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + this.mgt + "/actuator/info", Map.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	// @Test / sample test to use MockMVC    -- check below getHello_betterWay method
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().string(equalTo("Greetings from Spring Boot!")));
	}

	// @Test
	public void getHello_betterWay() throws Exception {
		ResponseEntity<String> response = testRestTemplate.getForEntity(base.toString(), String.class);
		assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
	}

}

/**
 * MockMvc comes from Spring Test and lets you, through a set of convenient
 * builder classes, send HTTP requests into the DispatcherServlet and make
 * assertions about the result. Note the use of @AutoConfigureMockMvc
 * and @SpringBootTest to inject a MockMvc instance. Having
 * used @SpringBootTest, we are asking for the whole application context to be
 * created. An alternative would be to ask Spring Boot to create only the web
 * layers of the context by using @WebMvcTest. In either case, Spring Boot
 * automatically tries to locate the main application class of your application,
 * but you can override it or narrow it down if you want to build something
 * different.
 * 
 * As well as mocking the HTTP request cycle, you can also use Spring Boot to
 * write a simple full-stack integration test. For example, instead of (or as
 * well as) the mock test shown earlier, we could create the following test
 * (from src/test/java/com/example/springboot/HelloControllerIT.java):
 */
