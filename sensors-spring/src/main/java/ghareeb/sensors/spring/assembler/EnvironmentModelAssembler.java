package ghareeb.sensors.spring.assembler;

import ghareeb.sensors.spring.controller.EnvironmentRestController;
import ghareeb.sensors.spring.controller.LocationRestController;
import ghareeb.sensors.spring.dto.EnvironmentModel;
import ghareeb.sensors.spring.dto.LocationModel;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnvironmentModelAssembler extends RepresentationModelAssemblerSupport<Environment, EnvironmentModel> {


    public EnvironmentModelAssembler() {
        super(EnvironmentRestController.class, EnvironmentModel.class);
    }

    @Override
    public EnvironmentModel toModel(Environment entity) {

        EnvironmentModel environmentModel = instantiateModel(entity);

        environmentModel.add(linkTo(methodOn(EnvironmentRestController.class).find(entity.getId()))
                .withSelfRel());

        environmentModel.setId(entity.getId());
        environmentModel.setAlarm(entity.isAlarm());
        environmentModel.setEmail(entity.getEmail());
        environmentModel.setName(entity.getName());
        environmentModel.setLocations(toLocationModel(entity.getLocations()));

        return environmentModel;
    }


    @Override
    public CollectionModel<EnvironmentModel> toCollectionModel(Iterable<? extends Environment> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(EnvironmentRestController.class).findAll()).withSelfRel());
    }

    private Set<LocationModel> toLocationModel(Set<Location> locations) {

        if (locations.isEmpty())
            return Collections.emptySet();

        return locations.stream().map(
                location -> LocationModel.builder()
                        .id(location.getId())
                        .abnormalHumidity(location.isAbnormalHumidity())
                        .abnormalLight(location.isAbnormalLight())
                        .abnormalTemperature(location.isAbnormalTemperature())
                        .build()
                        .add(linkTo(methodOn(LocationRestController.class).find(location.getId()))
                                .withSelfRel())

        ).collect(Collectors.toSet());
    }


}
