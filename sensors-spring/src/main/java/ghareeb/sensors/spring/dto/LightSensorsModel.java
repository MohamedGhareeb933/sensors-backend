package ghareeb.sensors.spring.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import ghareeb.sensors.spring.entity.Location;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Column;

@EqualsAndHashCode
@JsonRootName(value = "light sensors")
@Relation(collectionRelation = "light sensors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LightSensorsModel extends SensorsModel{

    private Float radiometry;
    private Float luminous;


    public LightSensorsModel(long id, Float min, Float max, Boolean active, Location location, Float radiometry, Float luminous) {
        super(id, min, max, active, location);
        this.radiometry = radiometry;
        this.luminous = luminous;
    }

    public LightSensorsModel() {
    }

    public Float getRadiometry() {
        return radiometry;
    }

    public void setRadiometry(Float radiometry) {
        this.radiometry = radiometry;
    }

    public Float getLuminous() {
        return luminous;
    }

    public void setLuminous(Float luminous) {
        this.luminous = luminous;
    }
}
