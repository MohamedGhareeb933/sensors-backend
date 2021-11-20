package ghareeb.sensors.spring.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import ghareeb.sensors.spring.entity.Location;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@EqualsAndHashCode
@JsonRootName(value = "humidity sensors")
@Relation(collectionRelation = "humidity sensors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HumiditySensorModel extends SensorsModel{

    private Float absolute;
    private Float relative;

    public HumiditySensorModel(long id, Float min, Float max, Boolean active, Location location, Float absolute, Float relative) {
        super(id, min, max, active, location);
        this.absolute = absolute;
        this.relative = relative;
    }

    public HumiditySensorModel(Float absolute, Float relative) {
        this.absolute = absolute;
        this.relative = relative;
    }

    public HumiditySensorModel() {
    }

    public Float getAbsolute() {
        return absolute;
    }

    public void setAbsolute(Float absolute) {
        this.absolute = absolute;
    }

    public Float getRelative() {
        return relative;
    }

    public void setRelative(Float relative) {
        this.relative = relative;
    }


}
