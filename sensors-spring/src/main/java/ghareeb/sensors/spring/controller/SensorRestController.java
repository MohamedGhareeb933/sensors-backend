package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dto.SensorsModel;
import ghareeb.sensors.spring.entity.Sensor;
import ghareeb.sensors.spring.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "Sensors")
public class SensorRestController  implements RestControllerHandler<SensorsModel, Sensor> {

    @Override
    public ResponseEntity<SensorsModel> find(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CollectionModel<SensorsModel>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<SensorsModel> save(Sensor payload) {
        return null;
    }

    @Override
    public ResponseEntity<SensorsModel> update(Sensor payload, Long id) {
        return null;
    }
}
