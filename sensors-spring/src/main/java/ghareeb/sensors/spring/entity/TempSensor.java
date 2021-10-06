package ghareeb.sensors.spring.entity;

import javax.persistence.*;

/**
 * temperature sensor subclass that extends from sensor
 * temp has temp column/property additional to all the properties in the parent class
 *
 */
@Entity(name = "temp_sensor")
public class TempSensor extends Sensor {

    @Column(name = "temp")
    private Float temp;

    public TempSensor() { }

    public TempSensor(float min, float max, boolean active, Location location, float temp) {
        super(min, max, active, location);
        this.temp = temp;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }
}
