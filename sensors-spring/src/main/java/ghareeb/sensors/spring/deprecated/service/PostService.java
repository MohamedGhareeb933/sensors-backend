package ghareeb.sensors.spring.deprecated.service;

import ghareeb.sensors.spring.deprecated.dto.Payload;
import ghareeb.sensors.spring.deprecated.dto.ResponseMessage;

public interface PostService {

    ResponseMessage postPayload(Payload payload);

}
