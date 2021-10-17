package ghareeb.sensors.spring;

import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationTest {

    private String BASEURL;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private Environment persistEnvironment;

    private Location persistLocation;

    private Location testLocation;

    @BeforeEach
    void setPersistFromGetMethodURL_InitialTestInstance() {

        BASEURL = "http://localhost:" + port;

        persistEnvironment = testRestTemplate.getForObject(BASEURL + "/environments/6", Environment.class);

        persistLocation = testRestTemplate.getForObject(BASEURL + "/locations/11", Location.class);

        testLocation = new Location("salon", false, false, false, persistEnvironment, null);

    }

    @Test
    void locationNameTest() {
        assertThat(persistLocation.getName()).contains("salon");
    }

    @Test
    void locationAbnormalTemperatureTest() {
        assertThat(persistLocation.isAbnormalTemperature()).isFalse();
    }

    @Test
    void locationAbnormalHumidityTest() {
        assertThat(persistLocation.isAbnormalHumidity()).isFalse();
    }

    @Test
    void locationLightHumidityTest() {
        assertThat(persistLocation.isAbnormalLight()).isFalse();
    }

}
