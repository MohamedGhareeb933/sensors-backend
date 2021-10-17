package ghareeb.sensors.spring;

import ghareeb.sensors.spring.entity.Environment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EnvironmentTest {

	private String BASEURL = " ";

	private Environment PersistEnvironment;

	private Environment TestEnvironment;


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@BeforeEach
	void setPersistEnvironmentFromGetMethodURL_InitialTestEnvironmentInstance() {

		BASEURL = "http://localhost:" + port;

		PersistEnvironment = testRestTemplate.getForObject(BASEURL + "/environments/6", Environment.class);

		TestEnvironment = new Environment(false,"mohamed@gmail.com", "shooting club", null);
	}

	@Test
	void GetEmailTest() throws IOException {
		assertThat(PersistEnvironment.getEmail()).contains(TestEnvironment.getEmail());
	}


	@Test
	void GetNameTest() throws IOException {
		assertThat(PersistEnvironment.getName()).contains(TestEnvironment.getName());
	}

	@Test
	void GetAlarmTest() throws IOException {
		assertThat(PersistEnvironment.isAlarm()).isEqualTo(TestEnvironment.isAlarm());
	}

	@Test
	void PostEnvironmentTest() {

		HttpEntity<Environment> request = new HttpEntity<>(TestEnvironment);

		ResponseEntity<String> response =
		testRestTemplate.postForEntity(BASEURL + "/add", request, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void Post_exchange_EnvironmentTest() {

		HttpEntity<Environment> request = new HttpEntity<>(TestEnvironment);

		ResponseEntity<Environment> response =
				testRestTemplate.exchange(BASEURL + "/add",HttpMethod.POST, request, Environment.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void PutEnvironmentTest() {

		HttpEntity<Environment> request = new HttpEntity<>(TestEnvironment);

		ResponseEntity<Environment> response =
				testRestTemplate.exchange(BASEURL + "/add",HttpMethod.POST, request, Environment.class);

		Map<String, String> param = new HashMap<>();
		param.put("id", String.format("%s", response.getBody().getId()));

		ResponseEntity<Environment> putResponse =
				testRestTemplate.exchange(BASEURL + "/environment/{id}", HttpMethod.PUT, request, Environment.class, param);

		assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

}
