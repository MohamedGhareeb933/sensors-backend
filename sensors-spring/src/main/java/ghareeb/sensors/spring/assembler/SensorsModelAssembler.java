package ghareeb.sensors.spring.assembler;

import ghareeb.sensors.spring.controller.SensorRestController;
import ghareeb.sensors.spring.dto.HumiditySensorModel;
import ghareeb.sensors.spring.dto.LightSensorsModel;
import ghareeb.sensors.spring.dto.SensorsModel;
import ghareeb.sensors.spring.dto.TempSensorModel;
import ghareeb.sensors.spring.entity.HumiditySensor;
import ghareeb.sensors.spring.entity.LightSensor;
import ghareeb.sensors.spring.entity.Sensor;
import ghareeb.sensors.spring.entity.TempSensor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SensorsModelAssembler extends RepresentationModelAssemblerSupport<Sensor, SensorsModel> {

    SensorsModelAssembler() {
        super(SensorRestController.class, SensorsModel.class);
    }

    @Override
    public SensorsModel toModel(Sensor entity) {

        if (entity instanceof HumiditySensor) {

            HumiditySensorModel humidityModel = (HumiditySensorModel) instantiateModel(entity);

            humidityModel.add(linkTo(methodOn(SensorRestController.class).find(entity.getId())).withSelfRel());

            extracted(entity, humidityModel);

            humidityModel.setRelative(((HumiditySensor) entity).getRelative());
            humidityModel.setAbsolute(((HumiditySensor) entity).getAbsolute());

            return humidityModel;

        } else if (entity instanceof LightSensor) {

            LightSensorsModel lightModel = (LightSensorsModel) instantiateModel(entity);

            lightModel.add(linkTo(methodOn(SensorRestController.class).find(entity.getId())).withSelfRel());

            extracted(entity, lightModel);

            lightModel.setRadiometry(((LightSensor) entity).getRadiometry());
            lightModel.setLuminous(((LightSensor) entity).getLuminous());

            return lightModel;

         } else {

            TempSensorModel tempModel =  (TempSensorModel) instantiateModel(entity);

            tempModel.add(linkTo(methodOn(SensorRestController.class).find(entity.getId())).withSelfRel());

            extracted(entity, tempModel);

            tempModel.setTemp( ((TempSensor) entity).getTemp());

            return tempModel;
        }

    }

    @Override
    public CollectionModel<SensorsModel> toCollectionModel(Iterable<? extends Sensor> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(SensorRestController.class).findAll()).withSelfRel());
    }

    //TODO RENAME THE METHOD.
    private void extracted(Sensor entity, SensorsModel sensorsModel) {
        sensorsModel.setId(entity.getId());
        sensorsModel.setMin(entity.getMin());
        sensorsModel.setMax(entity.getMax());
        sensorsModel.setActive(entity.isActive());
        sensorsModel.setLocation(entity.getLocation());
    }

}

