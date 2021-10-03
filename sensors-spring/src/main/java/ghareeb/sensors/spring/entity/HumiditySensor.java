package ghareeb.sensors.spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "humidity_sensor")
public class HumiditySensor extends Sensor{

    @Column(name = "absolute")
    private float absolute;

    @Column(name = "relative")
    private float relative;

}
