package ghareeb.sensors.spring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * environment entity - which mean whole place like house which has multiple rooms
 * has one-to-many with location that mean environment could have many locations.
 */
@Entity(name = "environment")
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "alarm")
    @NotNull
    private Boolean alarm;

    @Column(name = "email")
    @Email
    @NotNull
    private String email;

    @Column(name = "name")
    @Size(min = 2, max = 50)
    @NotNull
    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "environment")
    private Set<Location> locations = new HashSet<>();


    public Environment() {
    }

    public Environment(boolean alarm, String email, String name, Set<Location> locations) {
        this.alarm = alarm;
        this.email = email;
        this.name = name;
        this.locations = locations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public void add(Location location) {
        if (location != null ) {
            if (locations == null) {
                locations = new HashSet<>();
            }
            locations.add(location);
            location.setEnvironment(this);
        }
    }



}
