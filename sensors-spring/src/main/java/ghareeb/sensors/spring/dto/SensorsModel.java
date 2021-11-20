package ghareeb.sensors.spring.dto;


import ghareeb.sensors.spring.entity.Location;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public abstract class SensorsModel extends RepresentationModel<SensorsModel> {

    protected long id;
    protected Float min;
    protected Float max;
    protected Boolean active;
    protected Location location;


    public SensorsModel(long id, Float min, Float max, Boolean active, Location location) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.active = active;
        this.location = location;
    }

    public SensorsModel(Link initialLink, long id, Float min, Float max, Boolean active, Location location) {
        super(initialLink);
        this.id = id;
        this.min = min;
        this.max = max;
        this.active = active;
        this.location = location;
    }

    public SensorsModel(Iterable<Link> initialLinks, long id, Float min, Float max, Boolean active, Location location) {
        super(initialLinks);
        this.id = id;
        this.min = min;
        this.max = max;
        this.active = active;
        this.location = location;
    }

    public SensorsModel() {
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

    public Boolean getActive() {
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
