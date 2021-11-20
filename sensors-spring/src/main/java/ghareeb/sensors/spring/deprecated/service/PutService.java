package ghareeb.sensors.spring.deprecated.service;

import ghareeb.sensors.spring.deprecated.dto.Payload;
import ghareeb.sensors.spring.deprecated.dto.ResponseMessage;

public interface PutService {

    ResponseMessage updateEnvironment(Payload payload, long id);

    ResponseMessage updateLocation(Payload payload, long id);

    ResponseMessage updateHumiditySensor(Payload payload, long id);

    ResponseMessage updateLightSensor(Payload payload, long id);

    ResponseMessage updateTempSensor(Payload payload, long id);

}
