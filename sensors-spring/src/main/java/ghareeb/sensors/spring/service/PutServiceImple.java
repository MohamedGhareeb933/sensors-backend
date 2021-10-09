package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dao.SensorRepository;
import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;
import ghareeb.sensors.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Put/update Service Class - responsible for data updates
 *
 */
@Service
public class PutServiceImple implements PutService{

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SensorRepository sensorRepository;

    /**
     * Environment update
     * initialise environment from payload - find the environment from persistence
     * and check whether the payload/json object has a property and update it
     *
     * @param payload
     * @param id
     * @return
     */
    @Override
    public ResponseMessage updateEnvironment(Payload payload, long id) {

        Environment payloadEnvironment = payload.getEnvironment();

        environmentRepository.findById(id).map(
                environment -> {
                    if (payloadEnvironment.getEmail() != null) environment.setEmail(payloadEnvironment.getEmail());

                    if (payloadEnvironment.getName() != null) environment.setName(payloadEnvironment.getName());

                    if (payloadEnvironment.isAlarm() != null) environment.setAlarm(payloadEnvironment.isAlarm());

                    return environmentRepository.save(environment);
                });

        return new ResponseMessage("success");
    }

    /**
     * location update
     * deserialize location payload - find location from database
     * check for every property - in case it has payload / update
     * otherwise keep the data.
     *
     * @param payload
     * @param id
     * @return
     */
    @Override
    public ResponseMessage updateLocation(Payload payload, long id) {

        Location payloadLocation = payload.getLocation();

            locationRepository.findById(id).map(
                    location -> {

                        try {

                            if (payloadLocation.getName() != null)
                                location.setName(payloadLocation.getName());

                            if (payloadLocation.isAbnormalHumidity() != null)
                                location.setAbnormalHumidity(payloadLocation.isAbnormalHumidity());

                            if (payloadLocation.isAbnormalLight() != null)
                                location.setAbnormalLight(payloadLocation.isAbnormalLight());

                            if (payloadLocation.isAbnormalTemperature() != null)
                                location.setAbnormalTemperature(payloadLocation.isAbnormalTemperature());

                            if (payloadLocation.getEnvironment().getId() != 0)
                                location.setEnvironment(findEnvironment(payloadLocation));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return locationRepository.save(location);
                    });

        return new ResponseMessage("success");
    }

    /**
     * Sensor update
     * get Sensor Object from data transfer layer and find sensor from the database by id
     * check in case the sensor is a type of specific sensor, like humidity for example
     * then cast the child humidity class.
     *
     * method has update parent that update general properties like: min,max,active
     * and finally cast and update specific property for each sensor
     *
     * @param payload
     * @param id
     * @return
     */
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

    /**
     * find environment that exist in location payload
     *
     * @param location
     * @return
     * @throws Exception
     */
    private Environment findEnvironment(Location location) throws Exception {
        return environmentRepository.findById(location.getEnvironment().getId()).orElseThrow();
    }

    private Location findLocation(Sensor sensor) throws Exception {
        return locationRepository.findById(sensor.getLocation().getId()).orElseThrow();
    }

}
