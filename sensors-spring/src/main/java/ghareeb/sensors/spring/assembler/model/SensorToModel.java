package ghareeb.sensors.spring.assembler.model;


import ghareeb.sensors.spring.entity.*;
import ghareeb.sensors.spring.excpetion.SensorEntitiesNotFoundException;
import ghareeb.sensors.spring.model.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * sensor Model class to Each sensor abstract class to child class
 * then map each child/concrete class to Model class
 */
@Component
public class SensorToModel {

    public SensorsModel sensorEntityToModel(Sensor entity) {

        if (entity instanceof HumiditySensor) {

            HumiditySensor humiditySensor = (HumiditySensor) entity;

            HumiditySensorModel humidityModel = HumiditySensorModel.builder()
                    .absolute(humiditySensor.getAbsolute())
                    .relative(humiditySensor.getRelative())
                    .build();

            sensorEntityToModel(humiditySensor, humidityModel);

            return humidityModel;

        } else if (entity instanceof LightSensor) {

            LightSensor lightSensor = (LightSensor) entity;

            LightSensorsModel lightModel = LightSensorsModel.builder()
                    .radiometry(lightSensor.getRadiometry())
                    .luminous(lightSensor.getLuminous())
                    .build();

            sensorEntityToModel(lightSensor, lightModel);

            return lightModel;

        } else if (entity instanceof TempSensor){

            TempSensor tempSensor = (TempSensor) entity;

            TempSensorModel tempModel =  TempSensorModel.builder()
                    .temp(tempSensor.getTemp())
                    .build();

            sensorEntityToModel(tempSensor, tempModel);

            return tempModel;

        } else {
            throw new SensorEntitiesNotFoundException(String.format("cannot map %s to model", entity.toString()));
        }

    }

    private void sensorEntityToModel(Sensor sensor, SensorsModel sensorsModel) {
        sensorsModel.setId(sensor.getId());
        sensorsModel.setMin(sensor.getMin());
        sensorsModel.setMax(sensor.getMax());
        sensorsModel.setActive(sensor.isActive());
    }

}
