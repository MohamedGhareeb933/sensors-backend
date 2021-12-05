package ghareeb.sensors.spring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "location")
@Relation(collectionRelation = "locations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationModel extends RepresentationModel<LocationModel> {

    private Long id;
    private String name;
    private Boolean abnormalTemperature;
    private Boolean abnormalLight;
    private Boolean abnormalHumidity;
    private EnvironmentModel environment;
    private Set<SensorsModel> sensors;

}
