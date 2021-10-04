package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dao.SensorRepository;
import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImple implements SensorService{

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SensorRepository sensorRepository;


    @Override
    public ResponseMessage postPayload(Payload payload) {

        // TODO CHECK FOR PAYLOADS
        // TODO PUT AND DELETE METHODS
        // TODO HANDLE CASES

        Environment environment = payload.getEnvironment();
        Location location = payload.getLocation();

        environment.add(location);

        Sensor humidity = payload.getHumiditySensor();

        Sensor light = payload.getLightSensor();

        Sensor temp = payload.getTempSensor();

        location.add(humidity);
        location.add(light);
        location.add(temp);


        environmentRepository.save(environment);

        return new ResponseMessage("success");
    }


}
