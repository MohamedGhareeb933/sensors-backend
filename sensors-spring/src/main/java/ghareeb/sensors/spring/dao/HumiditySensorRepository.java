package ghareeb.sensors.spring.dao;


import ghareeb.sensors.spring.entity.HumiditySensor;

import javax.transaction.Transactional;

@Transactional
public interface HumiditySensorRepository extends SensorRepository<HumiditySensor> {

}
