package ghareeb.sensors.spring.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ghareeb.sensors.spring.deprecated.dto.Payload;
import ghareeb.sensors.spring.entity.HumiditySensor;
import ghareeb.sensors.spring.entity.LightSensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SensorTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void Setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .defaultRequest(get("/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")).build();
    }

    @Test
    void FindAll_Sensors_Test() throws Exception {
        this.mockMvc.perform(get("/sensors"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpectAll(
                        jsonPath("$._embedded.humidity[0].id").value(1),
                        jsonPath("$._embedded.light[0].id").value(2),
                        jsonPath("$._embedded.temp[0].id").value(3)
                );
    }

    @Test
    void Find_Sensors_Test() throws Exception {
        this.mockMvc.perform(get("/sensors/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpectAll(
                        jsonPath("$.id").value(1),
                        jsonPath("$.active").isBoolean(),
                        jsonPath("$.absolute").value(908.0)
                );
    }

    @Test
    void Save_Sensor_Test() throws Exception {

        HumiditySensor humiditySensor = new HumiditySensor();
        humiditySensor.setMin(10.0f);
        humiditySensor.setMax(50.0f);
        humiditySensor.setActive(true);
        humiditySensor.setAbsolute(900f);
        humiditySensor.setRelative(567f);

        Payload payload = new Payload();
        payload.setHumiditySensor(humiditySensor);

        this.mockMvc.perform(post("/sensors").content(asJsonString(payload)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpectAll(
                        jsonPath("$._embedded.humidity[0].id").isNotEmpty(),
                        jsonPath("$._embedded.humidity[0].min").value(10.0),
                        jsonPath("$._embedded.humidity[0].active").isBoolean(),
                        jsonPath("$._embedded.humidity[0].absolute").value(900.0)
                );
    }

    @Test
    void Update_Sensors_Test() throws Exception {

        LightSensor lightSensor = new LightSensor();
        lightSensor.setRadiometry(908f);
        lightSensor.setLuminous(890f);
        lightSensor.setActive(true);
        lightSensor.setMin(10f);
        lightSensor.setMax(80f);


        Payload payload = new Payload();
        payload.setLightSensor(lightSensor);

        this.mockMvc.perform(
                put("/sensors/{id}", 2).content(asJsonString(payload)))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpectAll(
                        jsonPath("$.min").value(10.0),
                        jsonPath("$.max").value(80.0),
                        jsonPath("$.active").isBoolean(),
                        jsonPath("$.radiometry").value(908.0),
                        jsonPath("$.luminous").value(890.0)
                        );
    }

    private static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
