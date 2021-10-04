package ghareeb.sensors.spring.entity;

import javax.persistence.*;

@Entity(name = "humidity_sensor")
public class HumiditySensor extends Sensor {

    @Column(name = "absolute")
    private float absolute;

    @Column(name = "relative")
    private float relative;

    public HumiditySensor() { }

    public HumiditySensor(float min, float max, boolean active, Location location, float absolute, float relative) {
        super(min, max, active, location);
        this.absolute = absolute;
        this.relative = relative;
    }

    public float getAbsolute() {
        return absolute;
    }

    public void setAbsolute(float absolute) {
        this.absolute = absolute;
    }

    public float getRelative() {
        return relative;
    }

    public void setRelative(float relative) {
        this.relative = relative;
    }
}

