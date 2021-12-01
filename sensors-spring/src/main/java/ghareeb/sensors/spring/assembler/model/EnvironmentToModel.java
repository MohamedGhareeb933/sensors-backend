package ghareeb.sensors.spring.assembler.model;

import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.excpetion.SensorEntitiesNotFoundException;
import ghareeb.sensors.spring.model.EnvironmentModel;
import ghareeb.sensors.spring.model.LocationModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EnvironmentToModel {

    public EnvironmentModel environmentEntityToModel(Environment environment) {

        if (environment == null)
            throw new SensorEntitiesNotFoundException(String.format("Environment entity %s is not found", environment.toString()));

        return EnvironmentModel.builder()
                .id(environment.getId())
                .email(environment.getEmail())
                .name(environment.getName())
                .alarm(environment.isAlarm())
                .build();

    }

    public EnvironmentModel environmentEntityToModel(Environment environment, Set<LocationModel> locations) {

        EnvironmentModel environmentModel = environmentEntityToModel(environment);

        environmentModel.setLocations(locations);

        return environmentModel;
    }

}
