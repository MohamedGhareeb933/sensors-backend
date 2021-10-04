package ghareeb.sensors.spring.dao;

import ghareeb.sensors.spring.entity.LightSensor;

import javax.transaction.Transactional;

@Transactional
public interface LightSensorRepository extends SensorRepository<LightSensor> {
}