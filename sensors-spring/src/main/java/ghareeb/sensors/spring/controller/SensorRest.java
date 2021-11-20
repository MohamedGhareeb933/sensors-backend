package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.entity.Sensor;
import ghareeb.sensors.spring.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "Sensors")
public class SensorRest {

    @Autowired
    SensorsService sensorsService;

    @GetMapping("/{id}")
    private Sensor find(@PathVariable Long id) {
        return sensorsService.find(id);
    }

    @GetMapping("/")
    private List<Sensor> findAll(Long id) {
        return sensorsService.findAll();
    }
}
