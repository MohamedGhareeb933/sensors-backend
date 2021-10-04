package ghareeb.sensors.spring.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

   @Column(name = "ab_temp")
   private boolean abnormalTemperature;

    @Column(name = "ab_light")
    private boolean abnormalLight;

    @Column(name = "ab_humidity")
    private boolean abnormalHumidity;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "environment_id")
    private Environment environment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
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

    public boolean isAbnormalTemperature() {
        return abnormalTemperature;
    }

    public void setAbnormalTemperature(boolean abnormalTemperature) {
        this.abnormalTemperature = abnormalTemperature;
    }

    public boolean isAbnormalLight() {
        return abnormalLight;
    }

    public void setAbnormalLight(boolean abnormalLight) {
        this.abnormalLight = abnormalLight;
    }

    public boolean isAbnormalHumidity() {
        return abnormalHumidity;
    }

    public void setAbnormalHumidity(boolean abnormalHumidity) {
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
