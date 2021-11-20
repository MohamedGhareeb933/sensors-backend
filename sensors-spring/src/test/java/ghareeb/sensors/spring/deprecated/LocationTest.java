package ghareeb.sensors.spring.deprecated;

import com.fasterxml.jackson.databind.ObjectMapper;
import ghareeb.sensors.spring.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@SpringBootTest
public class LocationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

   @BeforeEach
   void setup() {
       this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
               .defaultRequest(get("/locations")
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON)
                       .characterEncoding("utf-8")).build();
   }

    @Test
    void GetLocations_checkIfNameIsString_IdIsNotEmpty_SensorsIsArray_SizeIsGreaterThanOne() throws Exception {

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].name").isString())
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].sensors").isArray())
                .andExpect(jsonPath("$.[?(@.size() > 0)]").exists());
    }

    @Test
    void GetLocationById_checkFor_IdValue_Name_SensorsStates_SensorsArraySize() throws Exception {

        mockMvc.perform(get("/location/11"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpectAll(
                        jsonPath("$.id").value(11),
                        jsonPath("$.name").value("salon"),
                        jsonPath("$.abnormalTemperature").isBoolean(),
                        jsonPath("$.abnormalTemperature").value(false),
                        jsonPath("$.abnormalLight").isBoolean(),
                        jsonPath("$.abnormalLight").value(false),
                        jsonPath("$.abnormalHumidity").isBoolean(),
                        jsonPath("$.abnormalHumidity").value(false),
                        jsonPath("$.sensors[?(@.size() > 0)]").exists()
                );
    }

    @Test
    void PostLocation() throws Exception {

       Location location = new Location("club", false, false, false, null, null);

        MvcResult mvc = mockMvc.perform(
               post("/add")
                       .content(asJsonString(location))
       ).andDo(print()).andExpectAll(
               status().isOk(),
               content().json("{\"message\":\"success\"}")
       ).andReturn();
    }

    @Test
    void PutLocation() throws Exception {

        Location location = new Location();
        location.setName("club");
        location.setAbnormalHumidity(true);

        mockMvc.perform(
                put("/location/{id}", 11)
                        .content(asJsonString(location))
        ).andDo(print()).andExpectAll(
                status().isOk(),
                content().json("{\"message\":\"success\"}"),
                jsonPath("$.name").exists()
        );
    }

    public static String asJsonString(Object ob) {
       try {
           return new ObjectMapper().writeValueAsString(ob);
       } catch (Exception exc) {
           throw new RuntimeException("Object Mapper Can't write Value");
       }
    }
}
