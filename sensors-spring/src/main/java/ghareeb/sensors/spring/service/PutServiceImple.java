package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dao.SensorRepository;
import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO DOC
@Service
public class PutServiceImple implements PutService{

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SensorRepository sensorRepository;

    // TODO DOC
    @Override
    public ResponseMessage updateEnvironment(Payload payload, long id) {

        Environment jsonEnv = payload.getEnvironment();

        environmentRepository.findById(id).map(
                environment -> {
                    if (jsonEnv.getEmail() != null) environment.setEmail(jsonEnv.getEmail());

                    if (jsonEnv.getName() != null) environment.setName(jsonEnv.getName());

                    if (jsonEnv.isAlarm() != null) environment.setAlarm(jsonEnv.isAlarm());

                    return environmentRepository.save(environment);
                });

        return new ResponseMessage("success");
    }

    // TODO DOC
    @Override
    public ResponseMessage updateLocation(Payload payload, long id) {

        Location jsonLocation = payload.getLocation();

            locationRepository.findById(id).map(
                    location -> {

                        try {

                            if (jsonLocation.getName() != null)
                                location.setName(jsonLocation.getName());

                            if (jsonLocation.isAbnormalHumidity() != null)
                                location.setAbnormalHumidity(jsonLocation.isAbnormalHumidity());

                            if (jsonLocation.isAbnormalLight() != null)
                                location.setAbnormalLight(jsonLocation.isAbnormalLight());

                            if (jsonLocation.isAbnormalTemperature() != null)
                                location.setAbnormalTemperature(jsonLocation.isAbnormalTemperature());

                            if (jsonLocation.getEnvironment().getId() != 0)
                                location.setEnvironment(findEnvironment(jsonLocation));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return locationRepository.save(location);
                    });

        return new ResponseMessage("success");
    }

    // TODO DOC
    @Override
    public ResponseMessage updateHumiditySensor(Payload payload, long id) {

        Sensor humidity = payload.getHumiditySensor();

        sensorRepository.findById(id).map(
                sensor -> {
                    if (sensor instanceof HumiditySensor) {
                        HumiditySensor humiditySensor = (HumiditySensor) sensor;

                        updateParentSensor(humidity, humiditySensor);

                        if (((HumiditySensor) humidity).getAbsolute() != null)
                            humiditySensor.setAbsolute(((HumiditySensor) humidity).getAbsolute());

                        if (((HumiditySensor) humidity).getRelative() != null)
                                humiditySensor.setRelative(((HumiditySensor) humidity).getRelative());

                    }
                    return sensorRepository.save(sensor);
                });

        return new ResponseMessage("success");
    }

    // TODO DOC
    @Override
    public ResponseMessage updateLightSensor(Payload payload, long id) {
        Sensor light = payload.getLightSensor();

        sensorRepository.findById(id).map(
                sensor -> {
                    if (sensor instanceof LightSensor) {
                        LightSensor lightSensor = (LightSensor) sensor;

                        updateParentSensor(light, lightSensor);

                        if (((LightSensor) light).getRadiometry() != null)
                            lightSensor.setRadiometry(((LightSensor) light).getRadiometry());

                        if (((LightSensor) light).getLuminous() != null)
                                lightSensor.setLuminous(((LightSensor) light).getLuminous());

                    }
                    return sensorRepository.save(sensor);
                });

        return new ResponseMessage("success");
    }

    // TODO DOC
    @Override
    public ResponseMessage updateTempSensor(Payload payload, long id) {
        Sensor temp = payload.getTempSensor();

        sensorRepository.findById(id).map(
                sensor -> {
                    if (sensor instanceof TempSensor) {
                        TempSensor tempSensor = (TempSensor) sensor;

                        updateParentSensor(temp, tempSensor);

                        if (((TempSensor) temp).getTemp() != null)
                            tempSensor.setTemp(((TempSensor) temp).getTemp());

                    }
                    return sensorRepository.save(sensor);
                });

        return new ResponseMessage("success");
    }

    // TODO DOC
    private void updateParentSensor(Sensor fromPayload, Sensor fromPersist){

        try {
            if (fromPayload.getMin() != null)
                fromPersist.setMin(fromPayload.getMin());

            if (fromPayload.getMax() != null)
                fromPersist.setMax(fromPayload.getMax());

            if (fromPayload.isActive() != null)
                fromPersist.setActive(fromPayload.isActive());

            if (fromPayload.getLocation().getId() != 0)
                fromPersist.setLocation(findLocation(fromPayload));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // TODO DOC
    private Environment findEnvironment(Location location) throws Exception {
        return environmentRepository.findById(location.getEnvironment().getId()).orElseThrow();
    }

    private Location findLocation(Sensor sensor) throws Exception {
        return locationRepository.findById(sensor.getLocation().getId()).orElseThrow();
    }

}
