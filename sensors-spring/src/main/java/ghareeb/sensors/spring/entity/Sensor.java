package ghareeb.sensors.spring.entity;

import javax.persistence.*;

/**
 * parent class to humidity - light - temprature sensor
 * Inheritance type Joined which mean - the column which repeatedly appears in all column in all the tables
 * will be used for joining with sub class columns
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
    private float min;

    @Column(name = "max")
    private float max;

    @Column(name = "active")
    private boolean active;

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

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
