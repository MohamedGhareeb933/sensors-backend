package ghareeb.sensors.spring.model;


import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public abstract class SensorsModel extends RepresentationModel<SensorsModel> {

    private long id;
    private Float min;
    private Float max;
    private Boolean active;
    private LocationModel location;


    public SensorsModel(long id, Float min, Float max, Boolean active, LocationModel location) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.active = active;
        this.location = location;
    }

    public SensorsModel(Link initialLink, long id, Float min, Float max, Boolean active, LocationModel location) {
        super(initialLink);
        this.id = id;
        this.min = min;
        this.max = max;
        this.active = active;
        this.location = location;
    }

    public SensorsModel(Iterable<Link> initialLinks, long id, Float min, Float max, Boolean active, LocationModel location) {
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

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

}
