package ghareeb.sensors.spring.dao;

import ghareeb.sensors.spring.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SensorRepository<T extends Sensor> extends JpaRepository<T, Long> {
}
