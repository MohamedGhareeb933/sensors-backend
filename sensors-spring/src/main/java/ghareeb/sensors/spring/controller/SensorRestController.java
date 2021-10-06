package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.service.PostService;
import ghareeb.sensors.spring.service.PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.xpath.XPathVariableResolver;


/**
 *  the rest controller has add/post http method and call postPayload that save to entity and has the logic.
 */
@RestController
@CrossOrigin
public class SensorRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private PutService putService;

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

}
