package ghareeb.sensors.spring;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SensorsSpringApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGetJson() throws IOException {
		HttpUriRequest request = new HttpGet("localhost:8080/environments");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);
	}

}