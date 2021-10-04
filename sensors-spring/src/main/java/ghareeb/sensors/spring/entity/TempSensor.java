package ghareeb.sensors.spring.entity;

import javax.persistence.*;

@Entity(name = "temp_sensor")
public class TempSensor extends Sensor {

    @Column(name = "temp")
    private float temp;

    public TempSensor() { }

    public TempSensor(float min, float max, boolean active, Location location, float temp) {
        super(min, max, active, location);
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
}
