package ghareeb.sensors.spring.entity;

import javax.persistence.*;

/**
 *  sub class of sensor
 *  has radiometry and luminous to be joined with sensor columns 
 */
@Entity(name = "light_sensor")
public class LightSensor extends Sensor {

    @Column(name = "radiometry")
    private float radiometry;

    @Column(name = "luminous")
    private float luminous;

    public LightSensor() {}

    public LightSensor(float min, float max, boolean active, Location location, float radiometry, float luminous) {
        super(min, max, active, location);
        this.radiometry = radiometry;
        this.luminous = luminous;
    }

    public float getRadiometry() {
        return radiometry;
    }

    public void setRadiometry(float radiometry) {
        this.radiometry = radiometry;
    }

    public float getLuminous() {
        return luminous;
    }

    public void setLuminous(float luminous) {
        this.luminous = luminous;
    }
}
