package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/add", method = RequestMethod.POST)
public class SensorRestController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping(value = "/sensor", method = RequestMethod.POST)
    public ResponseMessage postPayload(@RequestBody Payload payload) {
        return sensorService.postPayload(payload);
    }


}
