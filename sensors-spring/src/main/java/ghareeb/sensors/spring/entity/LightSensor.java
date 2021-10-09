package ghareeb.sensors.spring.entity;

import javax.persistence.*;

/**
 *  subclass of sensor
 *  has radiometry and luminous to be joined with sensor columns 
 */
@Entity(name = "light_sensor")
public class LightSensor extends Sensor {

    @Column(name = "radiometry")
    private Float radiometry;

    @Column(name = "luminous")
    private Float luminous;

    public LightSensor() {}

    public LightSensor(float min, float max, boolean active, Location location, float radiometry, float luminous) {
        super(min, max, active, location);
        this.radiometry = radiometry;
        this.luminous = luminous;
    }

    public Float getRadiometry() {
        return radiometry;
    }

    public void setRadiometry(Float radiometry) {
        this.radiometry = radiometry;
    }

    public Float getLuminous() {
        return luminous;
    }

    public void setLuminous(Float luminous) {
        this.luminous = luminous;
    }
}
