package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import ghareeb.sensors.spring.service.PostService;
import ghareeb.sensors.spring.service.PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *  Rest controller class for add/post or update/put API
 *
 *  has post method api for saving the data - logic in the service class
 *  has put method for each entity and subclass
 *
 */
@RestController
@CrossOrigin
public class SensorRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private PutService putService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private LocationRepository locationRepository;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage postPayload(@RequestBody Payload payload) {
        return postService.postPayload(payload);
    }


    @RequestMapping(value = "/environment/{id}", method = RequestMethod.PUT)
    public ResponseMessage updateEnvironment(@RequestBody Payload payload, @PathVariable long id) {
        return putService.updateEnvironment(payload, id);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.PUT)
    public ResponseMessage updateLocation(@RequestBody Payload payload, @PathVariable long id) {
        return putService.updateLocation(payload, id);
    }

    @RequestMapping(value = "/humidity/{id}", method = RequestMethod.PUT)
    public ResponseMessage updateHumidity(@RequestBody Payload payload, @PathVariable long id) {
        return putService.updateHumiditySensor(payload, id);
    }

    @RequestMapping(value = "/light/{id}", method = RequestMethod.PUT)
    public ResponseMessage updateLight(@RequestBody Payload payload, @PathVariable long id) {
        return putService.updateLightSensor(payload, id);
    }

    @RequestMapping(value = "/temp/{id}", method = RequestMethod.PUT)
    public ResponseMessage updateTemp(@RequestBody Payload payload, @PathVariable long id) {
        return putService.updateTempSensor(payload, id);
    }

    @RequestMapping(value = "/getAllLocations", method = RequestMethod.GET)
    public List<Environment> getAllLocations() {
        return environmentRepository.findAll();
    }

}
