package ghareeb.sensors.spring.assembler;

import ghareeb.sensors.spring.assembler.model.LocationToModel;
import ghareeb.sensors.spring.controller.LocationRestController;
import ghareeb.sensors.spring.assembler.model.SensorToModel;
import ghareeb.sensors.spring.controller.SensorRestController;
import ghareeb.sensors.spring.model.*;
import ghareeb.sensors.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * subclass of representation Model assembler
 * and call LocationToModel class to map each Location Entity to Location Model
 * then add links to LocationModel
 *
 * has also toCollectionModel that accepts List of Location
 *
 * has to SensorModel that map Sensor List to Sensor Model by calling methods in SensorToModel.
 */
@Component
public class LocationModelAssembler extends RepresentationModelAssemblerSupport<Location, LocationModel> {

    @Autowired
    private SensorToModel sensorToModel;

    @Autowired
    private LocationToModel locationToModel;

    LocationModelAssembler() {
        super(LocationRestController.class, LocationModel.class);
    }

    @Override
    public LocationModel toModel(Location entity) {

        return locationToModel
                .locationEntityToModel(entity, toSensorsModel(entity.getSensors()))
                .add(linkTo(methodOn(LocationRestController.class).find(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(LocationRestController.class).findAll()).withRel("locations"));
    }

    @Override
    public CollectionModel<LocationModel> toCollectionModel(Iterable<? extends Location> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(LocationRestController.class).findAll()).withSelfRel());
    }

    private Set<SensorsModel> toSensorsModel(Set<Sensor> sensors) {
        if (sensors == null)
            return Collections.emptySet();

        return sensors.stream().map(
             sensor -> {
                 return sensorToModel.sensorEntityToModel(sensor)
                         .add(linkTo(methodOn(SensorRestController.class).find(sensor.getId())).withSelfRel())
                         .add(linkTo(methodOn(SensorRestController.class).findAll()).withRel("sensors"));
             }).collect(Collectors.toSet());
    }


}
