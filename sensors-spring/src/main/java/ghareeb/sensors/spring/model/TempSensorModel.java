package ghareeb.sensors.spring.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;


@Getter
@EqualsAndHashCode
@JsonRootName(value = "temp")
@Relation(collectionRelation = "temp")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TempSensorModel extends SensorsModel{

    private Float temp;

    @Builder
    public TempSensorModel(long id, Float min, Float max, Boolean active, LocationModel location, Float temp) {
        super(id, min, max, active, location);
        this.temp = temp;
    }

    public TempSensorModel() {
    }

}
