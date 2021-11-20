package ghareeb.sensors.spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * location entity has the boolean state of each case sensors detects
 * has many-to-one relation with environment - environment could have many locations
 * has one-to-many relation with sensors - location could have many types of sensors
 */

@Entity(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @Size(min = 2, max = 50)
    @NotNull
    private String name;

    @Column(name = "ab_temp")
    private Boolean abnormalTemperature;

    @Column(name = "ab_light")
    private Boolean abnormalLight;

    @Column(name = "ab_humidity")
    private Boolean abnormalHumidity;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "environment_id")
    private Environment environment;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    private Set<Sensor> sensors = new HashSet<>();


    public Location() {
    }

    public Location(String name, boolean abnormalTemperature, boolean abnormalLight, boolean abnormalHumidity, Environment environment, Set<Sensor> sensors) {
        this.name = name;
        this.abnormalTemperature = abnormalTemperature;
        this.abnormalLight = abnormalLight;
        this.abnormalHumidity = abnormalHumidity;
        this.environment = environment;
        this.sensors = sensors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAbnormalTemperature() {
        return abnormalTemperature;
    }

    public void setAbnormalTemperature(Boolean abnormalTemperature) {
        this.abnormalTemperature = abnormalTemperature;
    }

    public Boolean isAbnormalLight() {
        return abnormalLight;
    }

    public void setAbnormalLight(Boolean abnormalLight) {
        this.abnormalLight = abnormalLight;
    }

    public Boolean isAbnormalHumidity() {
        return abnormalHumidity;
    }

    public void setAbnormalHumidity(Boolean abnormalHumidity) {
        this.abnormalHumidity = abnormalHumidity;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        sensors = sensors;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void add(Sensor sensor) {
        if (sensor != null) {
            if (sensors == null) {
                sensors = new HashSet<>();
            }
            sensors.add(sensor);
            sensor.setLocation(this);
        }
    }

}
