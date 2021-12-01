package ghareeb.sensors.spring.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import ghareeb.sensors.spring.entity.HumiditySensor;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@Getter
@EqualsAndHashCode
@JsonRootName(value = "humidity")
@Relation(collectionRelation = "humidity")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HumiditySensorModel extends SensorsModel{

    private Float absolute;
    private Float relative;

    @Builder
    public HumiditySensorModel(long id, Float min, Float max, Boolean active, LocationModel location, Float absolute, Float relative) {
        super(id, min, max, active, location);
        this.absolute = absolute;
        this.relative = relative;
    }

    public HumiditySensorModel() {
    }

}
