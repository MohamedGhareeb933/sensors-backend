package ghareeb.sensors.spring.test;

import ghareeb.sensors.spring.assembler.EnvironmentModelAssembler;
import ghareeb.sensors.spring.entity.Environment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnvironmentTest {

    String BASEURL = "";

    Environment persistEnvironment;

    Environment testEnvironment;

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    EnvironmentModelAssembler  assembler;

    @BeforeEach
    void Setup() {

        BASEURL = "http://localhost:" + port;

        persistEnvironment = testRestTemplate.getForObject(BASEURL + "/environments/1", Environment.class);

        testEnvironment = new Environment();
        testEnvironment.setEmail("mo@gmail.com");
        testEnvironment.setAlarm(false);
        testEnvironment.setName("shootin club");
    }

    @Test
    void EnvironmentEmail_Test() throws IOException {
        Assertions.assertThat(persistEnvironment.getEmail()).isEqualTo(testEnvironment.getEmail());
    }

    @Test
    void EnvironmentAlarm_Test() throws IOException {
        Assertions.assertThat(persistEnvironment.isAlarm()).isEqualTo(testEnvironment.isAlarm());
    }

    @Test
    void EnvironmentName_Test() throws IOException {
        Assertions.assertThat(persistEnvironment.getName()).isEqualTo(testEnvironment.getName());
    }

    @Test
    void Environment_Post_test() throws IOException {

        HttpEntity<Environment> request = new HttpEntity<>(testEnvironment);

        ResponseEntity<CollectionModel> response = testRestTemplate
                .postForEntity(BASEURL + "/environments", request, CollectionModel.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void Environment_Put_test() {

        HttpEntity<Environment> request = new HttpEntity<>(testEnvironment);

        ResponseEntity<CollectionModel> postResponse = testRestTemplate.postForEntity(BASEURL + "/environments", request, CollectionModel.class);

        Integer id = 0;
        for (Object object : postResponse.getBody().getContent()) {
            id = (Integer) ((LinkedHashMap) object).get("id");
        }

        Map<String, String> param = new HashMap<>();
        param.put("id", String.format("%s", Objects.requireNonNull(id)));

        ResponseEntity<Environment> putResponse = testRestTemplate.exchange(BASEURL + "/environments/{id}", HttpMethod.PUT, request, Environment.class, param);

        Assertions.assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

}
