package ghareeb.sensors.spring.assembler.model;

import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import ghareeb.sensors.spring.excpetion.SensorEntitiesNotFoundException;
import ghareeb.sensors.spring.model.LocationModel;
import ghareeb.sensors.spring.model.SensorsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Location model class that map Location Entity to LocationModel class
 * has method overloading for additional parameter : sensorModel list and Environment
 */
@Component
public class LocationToModel {

    @Autowired
    EnvironmentToModel environmentToModel;

    public LocationModel locationEntityToModel(Location location) {

        if (location == null)
            throw new SensorEntitiesNotFoundException(String.format("%s is not found", location.toString()));

        return LocationModel.builder()
                .id(location.getId())
                .name(location.getName())
                .abnormalHumidity(location.isAbnormalHumidity())
                .abnormalLight(location.isAbnormalLight())
                .abnormalTemperature(location.isAbnormalTemperature())
                .build();
    }

    public LocationModel locationEntityToModel(Location location, Set<SensorsModel> sensorsModels) {

        LocationModel locationModel = this.locationEntityToModel(location);

        locationModel.setSensors(sensorsModels);

        return locationModel;
    }

    public LocationModel locationEntityToModel(Location location, Environment environment) {

        LocationModel locationModel = this.locationEntityToModel(location);

        locationModel.setEnvironment(environmentToModel.environmentEntityToModel(environment));

        return locationModel;
    }
}
