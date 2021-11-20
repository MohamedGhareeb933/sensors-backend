package ghareeb.sensors.spring.assembler;

import ghareeb.sensors.spring.controller.EnvironmentRestController;
import ghareeb.sensors.spring.controller.LocationRestController;
import ghareeb.sensors.spring.controller.SensorRestController;
import ghareeb.sensors.spring.dto.*;
import ghareeb.sensors.spring.entity.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LocationModelAssembler extends RepresentationModelAssemblerSupport<Location, LocationModel> {

    LocationModelAssembler() {
        super(LocationRestController.class, LocationModel.class);
    }

    @Override
    public LocationModel toModel(Location entity) {

        // instantiate LocationModel Bean
        LocationModel locationModel = instantiateModel(entity);

        // add selfLink to Location Model
        locationModel.add(linkTo(methodOn(LocationRestController.class).find(entity.getId())).withSelfRel());

        // set the locationModel from Location Entity
        locationModel.setId(entity.getId());
        locationModel.setName(entity.getName());
        locationModel.setAbnormalTemperature(entity.isAbnormalTemperature());
        locationModel.setAbnormalHumidity(entity.isAbnormalHumidity());
        locationModel.setAbnormalLight(entity.isAbnormalLight());
        locationModel.setEnvironment(toEnvironmentModel(entity.getEnvironment()));
        locationModel.setSensors(toSensorsModel(entity.getSensors()));

        return locationModel;
    }

    @Override
    public CollectionModel<LocationModel> toCollectionModel(Iterable<? extends Location> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(LocationRestController.class).findAll()).withSelfRel());
    }

    private EnvironmentModel toEnvironmentModel(Environment environment) {

        if (environment != null) {
            return EnvironmentModel.builder()
                    .id(environment.getId())
                    .email(environment.getEmail())
                    .name(environment.getName())
                    .alarm(environment.isAlarm())
                    .build()
                    .add(linkTo(methodOn(EnvironmentRestController.class).find(environment.getId())).withSelfRel());
        }
        return new EnvironmentModel();
    }

    private Set<SensorsModel> toSensorsModel(Set<Sensor> sensors) {

        if (sensors == null)
            return Collections.emptySet();

        return sensors.stream().map(
             sensor -> {
                 if (sensor instanceof HumiditySensor) {
                     HumiditySensor humidity = (HumiditySensor) sensor;
                     HumiditySensorModel humidityModel = new HumiditySensorModel();

                     extracted(humidity, humidityModel);

                     humidityModel.setAbsolute(humidity.getAbsolute());
                     humidityModel.setRelative(humidity.getRelative());

                     humidityModel.add(linkTo(methodOn(SensorRestController.class).find(humidity.getId())).withSelfRel());
                     return humidityModel;

                 } else if (sensor instanceof LightSensor) {

                     LightSensor light = (LightSensor) sensor;
                     LightSensorsModel lightModel = new LightSensorsModel();

                     extracted(light, lightModel);

                     lightModel.setLuminous(light.getLuminous());
                     lightModel.setRadiometry(light.getRadiometry());

                     lightModel.add(linkTo(methodOn(SensorRestController.class).find(light.getId())).withSelfRel());
                     return lightModel;

                 } else {

                     TempSensor temp = (TempSensor) sensor;
                     TempSensorModel tempModel = new TempSensorModel();

                     extracted(temp, tempModel);

                     tempModel.setTemp(temp.getTemp());

                     tempModel.add(linkTo(methodOn(SensorRestController.class).find(temp.getId())).withSelfRel());
                     return tempModel;
                 }
             }).collect(Collectors.toSet());

    }

    // TODO RENAME THE ARGUMENTS AND METHOD NAME
    private void extracted(Sensor sensor, SensorsModel sensorModel) {

        sensorModel.setId(sensorModel.getId());
        sensorModel.setActive(sensor.isActive());
        sensorModel.setMin(sensor.getMin());
        sensorModel.setMax(sensor.getMax());

    }

}
