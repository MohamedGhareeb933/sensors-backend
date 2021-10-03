package ghareeb.sensors.spring.dao;

import ghareeb.sensors.spring.entity.LightSensor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface LightSensorRepository extends SensorRepository<LightSensor> {
}