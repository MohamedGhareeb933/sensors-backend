package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;

public interface PutService {

    ResponseMessage updateEnvironment(Payload payload, long id);

    ResponseMessage updateLocation(Payload payload, long id);

    ResponseMessage updateHumiditySensor(Payload payload, long id);

    ResponseMessage updateLightSensor(Payload payload, long id);

    ResponseMessage updateTempSensor(Payload payload, long id);

}
