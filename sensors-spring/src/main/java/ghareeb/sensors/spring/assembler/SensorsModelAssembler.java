package ghareeb.sensors.spring.assembler;

import ghareeb.sensors.spring.controller.SensorRestController;
import ghareeb.sensors.spring.entity.*;
import ghareeb.sensors.spring.assembler.model.SensorToModel;
import ghareeb.sensors.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


//TODO DOCUMENT THE ASSEMBLER CLASS AND EXPLAIN ITS LOGIC
@Component
public class SensorsModelAssembler extends RepresentationModelAssemblerSupport<Sensor, SensorsModel> {

    @Autowired
    private SensorToModel sensorToModel;

    SensorsModelAssembler() {
        super(SensorRestController.class, SensorsModel.class);
    }

    @Override
    public SensorsModel toModel(Sensor entity) {

        return sensorToModel
                .sensorEntityToModel(entity)
                .add(linkTo(methodOn(SensorRestController.class).find(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(SensorRestController.class).findAll()).withRel("sensors"));
    }

    @Override
    public CollectionModel<SensorsModel> toCollectionModel(Iterable<? extends Sensor> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(SensorRestController.class).findAll()).withSelfRel());
    }


}

