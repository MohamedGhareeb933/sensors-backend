package ghareeb.sensors.spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "temp_sensor")
public class TempSensor extends Sensor {

    @Column(name = "temp")
    private float temp;

}
