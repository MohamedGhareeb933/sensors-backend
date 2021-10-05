package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *  the rest controller has add/post http method and call postPayload that save to entity and has the logic.
 */
@RestController
@CrossOrigin
public class SensorRestController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage postPayload(@RequestBody Payload payload) {
        return sensorService.postPayload(payload);
    }


}
