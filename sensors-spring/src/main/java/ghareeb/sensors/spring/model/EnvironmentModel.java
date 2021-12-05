package ghareeb.sensors.spring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "environment")
@Relation(collectionRelation = "environments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnvironmentModel extends RepresentationModel<EnvironmentModel> {

    private Long id;
    private Boolean alarm;
    private String email;
    private String name;
    private Set<LocationModel> locations;

}
