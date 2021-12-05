package ghareeb.sensors.spring.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import ghareeb.sensors.spring.entity.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LocationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void Setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .build();
    }

    @Test
    void FindAll_AllLocations_Test() throws Exception {
        this.mockMvc.perform(get("/locations")).andExpect(status().isAccepted()).andDo(print())
                .andExpect(jsonPath("$._embedded.locations[0].name").value("youth hall"))
                .andExpect(jsonPath("$._embedded.locations[0].sensors[0].id").value(2))
                .andExpect(jsonPath("$._embedded.locations[1].name").value("club house"))
                .andExpect(jsonPath("$._embedded.locations[0].name").isString())
                .andExpect(jsonPath("$._embedded.locations[*].id").isNotEmpty())
                .andExpect(jsonPath("$._embedded.locations[*].sensors").isArray())
                .andExpect(jsonPath("$._embedded.[?(@.size() > 0)]").exists());
    }

    @Test
    void Find_Location_test() throws Exception {
        this.mockMvc.perform(get("/locations/1")).andExpect(status().isOk()).andDo(print())
                .andExpectAll(
                        jsonPath("$.name").value("youth hall"),
                        jsonPath("$.id").value(1),
                        jsonPath("$.sensors[0].id").value(2)
                );

    }

   /*
    @Test
    void Post_Locations() throws Exception {

        Location location = new Location();
        location.setName("saloon");
        location.setAbnormalHumidity(false);
        location.setAbnormalTemperature(false);
        location.setAbnormalTemperature(false);

        Environment environment = new Environment();
        environment.setEmail("mo@gmail.com");
        environment.setName("shooting club");
        environment.setAlarm(false);

        MvcResult envResult = this.mockMvc.perform(post("/environments")
                        .content(asJsonString(environment)))
                .andDo(print())
                .andReturn();


        Map envResponse =(LinkedHashMap) jsonToObject(envResult.getResponse().getContentAsString());
        Map Embedded = (LinkedHashMap) envResponse.get("_embedded");
        List environments = (ArrayList) Embedded.get("environments");
        Map secHashMap = (LinkedHashMap) environments.get(0);

        //location.setEnvironment((Environment) environments.get(0));

         MvcResult mvc = this.mockMvc.perform(post("/locations").content(asJsonString(location)))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn();

    }
    */

    @Test
    void Post_Locations() throws Exception {

        Location location = new Location();
        location.setName("saloon");
        location.setAbnormalHumidity(false);
        location.setAbnormalTemperature(false);
        location.setAbnormalTemperature(false);
        location.setEnvironment(new Environment());
        location.setSensors(new HashSet<Sensor>());

        MvcResult mvc = this.mockMvc.perform(post("/locations").content(asJsonString(location)))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$._embedded.locations[0].id").isNumber(),
                        jsonPath("$._embedded.locations[0].id").isNotEmpty(),
                        jsonPath("$._embedded.locations[0].name").value("saloon"),
                        jsonPath("$._embedded.locations[0].sensors").isArray()
                        ).andReturn();
    }

    @Test
    void Put_location() throws Exception {

        Location location = new Location();
        location.setName("saloon");
        location.setAbnormalHumidity(false);
        location.setAbnormalTemperature(false);
        location.setAbnormalTemperature(false);

        this.mockMvc.perform(
                put("/locations/{id}", 3)
                .content(asJsonString(location)))
                .andDo(print())
                .andExpect(status().isAccepted());
    }


    public static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot map Object to String");
        }
    }

    public static Object jsonToObject(String json) {
        try {
            return new ObjectMapper().readValue(json, Object.class);
        }catch (JsonProcessingException e) {
           throw new RuntimeException("cannot Map Json to Object");
        }
    }

}
