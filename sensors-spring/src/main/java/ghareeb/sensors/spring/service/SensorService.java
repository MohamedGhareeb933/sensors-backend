package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.dto.Payload;
import ghareeb.sensors.spring.dto.ResponseMessage;

public interface SensorService {

    ResponseMessage postPayload(Payload payload);
}
