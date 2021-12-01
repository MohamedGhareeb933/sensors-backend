package ghareeb.sensors.spring.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@Getter
@EqualsAndHashCode
@JsonRootName(value = "light")
@Relation(collectionRelation = "light")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LightSensorsModel extends SensorsModel{

    private Float radiometry;
    private Float luminous;


    @Builder
    public LightSensorsModel(long id, Float min, Float max, Boolean active, LocationModel location, Float radiometry, Float luminous) {
        super(id, min, max, active, location);
        this.radiometry = radiometry;
        this.luminous = luminous;
    }

    public LightSensorsModel() {
    }

}
