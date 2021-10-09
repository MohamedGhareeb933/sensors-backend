package ghareeb.sensors.spring.entity;

import javax.persistence.*;

/**
 * parent class to humidity - light - temperature sensor
 * Inheritance type Joined which mean - the column which repeatedly appears in all column in all the tables
 * will be used for joining with subclass columns
 *
 * sensor has many-to-one relation with location
 */
@Entity(name = "sensor")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "min")
    private Float min;

    @Column(name = "max")
    private Float max;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "location_id")
    private Location location;

    public Sensor() {
    }

    public Sensor(float min, float max, boolean active, Location location) {
        this.min = min;
        this.max = max;
        this.active = active;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
