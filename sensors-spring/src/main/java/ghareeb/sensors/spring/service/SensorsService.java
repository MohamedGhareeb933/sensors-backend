package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.assembler.SensorsModelAssembler;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dao.SensorRepository;
import ghareeb.sensors.spring.dto.SensorsModel;
import ghareeb.sensors.spring.entity.HumiditySensor;
import ghareeb.sensors.spring.entity.LightSensor;
import ghareeb.sensors.spring.entity.Sensor;
import ghareeb.sensors.spring.entity.TempSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("sensorService")
public class SensorsService /* TODO - implements RestService<SensorsModel, Sensor> */{


    @Autowired
    LocationRepository locationRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorsModelAssembler assembler;


    public Sensor find(Long id) {
        return (Sensor) sensorRepository.findById(id).get();
    }

    public List<Sensor> findAll() {
        return (List<Sensor>) sensorRepository.findAll();
    }

    /*
    @Override
    public ResponseEntity<SensorsModel> find(Long id) {
        return sensorRepository.findById(id)
                .map(this::castSensor)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("sensor with id %s is not found", id)));



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

    private Sensor castSensor(Sensor sensor) {

        if (sensor instanceof HumiditySensor) {
            return (HumiditySensor) sensor;
        }else if (sensor instanceof LightSensor) {
            return (LightSensor) sensor;
        } else {
            return (TempSensor) sensor;
        }

    }
*/


}
