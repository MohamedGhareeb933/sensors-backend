package ghareeb.sensors.spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "light_sensor")
public class LightSensor extends Sensor {

    @Column(name = "radiometry")
    private float radiometry;

    @Column(name = "luminous")
    private float luminous;

}
