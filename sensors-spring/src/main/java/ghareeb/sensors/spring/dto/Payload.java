package ghareeb.sensors.spring.dto;

import ghareeb.sensors.spring.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * data transfer object
 * responsible for getting data from json
 * has environment, location and each type of sensor
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

    private Environment environment;

    private Location location;

    private HumiditySensor humiditySensor;

    private LightSensor lightSensor;

    private TempSensor tempSensor;

}
