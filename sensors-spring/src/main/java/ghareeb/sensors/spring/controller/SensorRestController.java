
package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.deprecated.dto.Payload;
import ghareeb.sensors.spring.model.SensorsModel;
import ghareeb.sensors.spring.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/sensors")
public class SensorRestController implements RestControllerHandler<SensorsModel, Payload> {

    @Autowired
    @Qualifier("sensorService")
    private RestService<SensorsModel, Payload> sensorsService;


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SensorsModel> find(@PathVariable Long id) {
        return sensorsService.find(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<SensorsModel>> findAll() {
        return sensorsService.findAll();
    }

    @Override
    @PostMapping
    public ResponseEntity<CollectionModel<SensorsModel>> save(@RequestBody Payload payload) {
        return sensorsService.save(payload);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SensorsModel> update(@RequestBody Payload payload,@PathVariable Long id) {
        return sensorsService.update(payload, id);
    }


}
