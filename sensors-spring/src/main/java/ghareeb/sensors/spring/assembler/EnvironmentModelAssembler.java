package ghareeb.sensors.spring.assembler;

import ghareeb.sensors.spring.assembler.model.EnvironmentToModel;
import ghareeb.sensors.spring.assembler.model.LocationToModel;
import ghareeb.sensors.spring.controller.EnvironmentRestController;
import ghareeb.sensors.spring.controller.LocationRestController;
import ghareeb.sensors.spring.model.EnvironmentModel;
import ghareeb.sensors.spring.model.LocationModel;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// TODO DOCUMENT
@Component
public class EnvironmentModelAssembler extends RepresentationModelAssemblerSupport<Environment, EnvironmentModel> {

    @Autowired
    private EnvironmentToModel environmentToModel;

    @Autowired
    private LocationToModel locationToModel;

    public EnvironmentModelAssembler() {
        super(EnvironmentRestController.class, EnvironmentModel.class);
    }

    @Override
    public EnvironmentModel toModel(Environment entity) {

        return environmentToModel
                .environmentEntityToModel(entity, toLocationModel(entity.getLocations()))
                .add(linkTo(methodOn(EnvironmentRestController.class).find(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(EnvironmentRestController.class).findAll()).withRel("environments"));
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
                location -> {
                    return locationToModel.locationEntityToModel(location)
                            .add(linkTo(methodOn(LocationRestController.class).find(location.getId())).withSelfRel())
                            .add(linkTo(methodOn(LocationRestController.class).findAll()).withRel("locations"));
                }
        ).collect(Collectors.toSet());
    }


}
