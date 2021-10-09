package ghareeb.sensors.spring.entity;

import javax.persistence.*;

/**
 * subclass of sensor
 * has its own columns which is absolute and relative to be joined with the parent class column
 *
 */
@Entity(name = "humidity_sensor")
public class HumiditySensor extends Sensor {

    @Column(name = "absolute")
    private Float absolute;

    @Column(name = "relative")
    private Float relative;

    public HumiditySensor() { }

    public HumiditySensor(float min, float max, boolean active, Location location, float absolute, float relative) {
        super(min, max, active, location);
        this.absolute = absolute;
        this.relative = relative;
    }

    public Float getAbsolute() {
        return absolute;
    }

    public void setAbsolute(Float absolute) {
        this.absolute = absolute;
    }

    public Float getRelative() {
        return relative;
    }

    public void setRelative(Float relative) {
        this.relative = relative;
    }
}

