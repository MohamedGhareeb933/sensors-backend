package ghareeb.sensors.spring.dao;

import ghareeb.sensors.spring.entity.TempSensor;

import javax.transaction.Transactional;

@Transactional
public interface TempSensorRepository extends SensorRepository<TempSensor> {
}