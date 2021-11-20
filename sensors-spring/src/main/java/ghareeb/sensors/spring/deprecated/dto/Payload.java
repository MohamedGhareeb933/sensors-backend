package ghareeb.sensors.spring.deprecated.dto;

import ghareeb.sensors.spring.entity.*;


/**
 * data transfer object
 * responsible for getting data from json
 * has environment, location and each type of sensor
 *
 */
public class Payload {

    private Environment environment;

    private Location location;

    private HumiditySensor humiditySensor;

    private LightSensor lightSensor;

    private TempSensor tempSensor;

    public Payload() {
    }

    public Payload(Environment environment, Location location, HumiditySensor humiditySensor, LightSensor lightSensor, TempSensor tempSensor) {
        this.environment = environment;
        this.location = location;
        this.humiditySensor = humiditySensor;
        this.lightSensor = lightSensor;
        this.tempSensor = tempSensor;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public HumiditySensor getHumiditySensor() {
        return humiditySensor;
    }

    public void setHumiditySensor(HumiditySensor humiditySensor) {
        this.humiditySensor = humiditySensor;
    }

    public LightSensor getLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(LightSensor lightSensor) {
        this.lightSensor = lightSensor;
    }

    public TempSensor getTempSensor() {
        return tempSensor;
    }

    public void setTempSensor(TempSensor tempSensor) {
        this.tempSensor = tempSensor;
    }
}
