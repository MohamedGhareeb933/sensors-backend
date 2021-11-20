package ghareeb.sensors.spring.deprecated.service;

import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dao.SensorRepository;
import ghareeb.sensors.spring.deprecated.dto.Payload;
import ghareeb.sensors.spring.deprecated.dto.ResponseMessage;
import ghareeb.sensors.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * service layer that has all Dao Repositories and save objects
 *
 */
//@Service("sensorPostService")
public class PostServiceImple implements PostService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SensorRepository sensorRepository;

    // TODO PUT AND DELETE METHODS

    /**
     * check for every payload existence then save them
     * also the method call handle payload cases
     *
     * @param payload
     * @return ResponseMessage("Message")
     */
    @Override
    public ResponseMessage postPayload(Payload payload) {

        if (payload.getEnvironment() != null) {
            Environment environment = handleEnvironmentPayload(payload);
            environmentRepository.save(environment);
        } else if (payload.getLocation() != null) {
            Location location = handleLocationPayload(payload);
            handleLocationEnvironment(location);
            locationRepository.save(location);
        } else {

            if (payload.getHumiditySensor() != null) {
                Sensor humidity = humiditySensorPayload(payload);
                handleSensorLocation(humidity);
                sensorRepository.save(humidity);
            }
            if (payload.getLightSensor() != null) {
                Sensor light = lightSensorPayload(payload);
                handleSensorLocation(light);
                sensorRepository.save(light);
            }
            if (payload.getTempSensor() != null) {
                Sensor temp = tempSensorPayload(payload);
                handleSensorLocation(temp);
                sensorRepository.save(temp);
            }

        }

        return new ResponseMessage("success");
    }


    /**
     * if the sensor posted individually it will require location id
     * to add sensors to location .
     * otherwise, throw an error
     *
     * @param sensor
     * @return Sensor
     */
    private Sensor handleSensorLocation(Sensor sensor) {
        try {
            if (sensor.getLocation().getId() == 0) {
                throw new Exception("Sensor must Attached to Location");
            }
            Location location = findLocation(sensor);
            sensor.setLocation(location);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sensor;

    }

    /**
     * if the location posted Alone - it will require environment id
     * to add location to the environment
     * otherwise throw an error
     *
     * @param location
     * @return Location
     */
    private Location handleLocationEnvironment(Location location) {
        try {
            if (location.getEnvironment().getId() == 0) {
                throw new Exception("location must be found in environment ");
            }
            Environment environment = findEnvironment(location);
            location.setEnvironment(environment);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * deserialize Json and set Environment
     * in case we have location in the payload/Json we add the location to the environment
     *
     * @param payload
     * @return Environment
     */
    private Environment handleEnvironmentPayload(Payload payload) {

        Environment environment = payload.getEnvironment();

            if (payload.getLocation() != null) {
                Location location = handleLocationPayload(payload);
                environment.add(location);
            }

        return environment;
    }

    /**
     * set the location from payload/Json/deserialize
     * and check in case we have sensors - we add sensors to the location
     *
     * @param payload
     * @return Location
     */
    private Location handleLocationPayload(Payload payload){

        Location location = payload.getLocation();

        if (payload.getHumiditySensor() != null) {
            Sensor humidity = humiditySensorPayload(payload);
            location.add(humidity);
        }
        if (payload.getLightSensor() != null) {
            Sensor light = lightSensorPayload(payload);
            location.add(light);
        }
        if (payload.getTempSensor() != null) {
            Sensor temp = tempSensorPayload(payload);
            location.add(temp);
        }

        return location;
    }

    /**
     * each sensor has a type in the data transfer object/dto
     * get each sensor type and return it
     *
     * @param payload
     * @return
     */
    private Sensor humiditySensorPayload(Payload payload) {
        return payload.getHumiditySensor();
    }

    private Sensor lightSensorPayload(Payload payload) {
        return payload.getLightSensor();
    }

    private Sensor tempSensorPayload(Payload payload) {
        return payload.getTempSensor();
    }

    /**
     *  find entity by id
     *  in the findby parameter we pass the id of the class we want to find
     *
     * @param location
     * @return
     * @throws Exception
     */
    private Environment findEnvironment(Location location) throws Exception {
        return environmentRepository.findById(location.getEnvironment().getId()).orElseThrow();
    }

    private Location findLocation(Sensor humidity) throws Exception {
        return locationRepository.findById(humidity.getLocation().getId()).orElseThrow();
    }

}
