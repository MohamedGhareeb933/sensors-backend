package ghareeb.sensors.spring.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import ghareeb.sensors.spring.entity.Location;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;


@EqualsAndHashCode
@JsonRootName(value = "temp sensors")
@Relation(collectionRelation = "temp sensors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TempSensorModel extends SensorsModel{

    private Float temp;


    public TempSensorModel(long id, Float min, Float max, Boolean active, Location location, Float temp) {
        super(id, min, max, active, location);
        this.temp = temp;
    }

    public TempSensorModel() {
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }
}
