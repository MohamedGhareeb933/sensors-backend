-- PREDEFINED DATA FOR SENSORS-INHERITANCE

-- ENVIROMENT 
INSERT INTO `environment` (`id`, `name`, `alarm`, `email`) VALUES ('1', 'ELSAID', '0', 'mohamed@gmail.com');

-- LOCATION 
INSERT INTO `location` (`id`, `name`, `ab_temp`, `ab_light`, `ab_humidity`, `environment_id`) VALUES ('1', 'salon', '0', '0', '0', '1');

-- SENSORS
INSERT INTO `sensor` (`id`, `min`, `max`, `active`, `location_id`) VALUES ('1', '10', '20', '1', '1');
INSERT INTO `sensor` (`id`, `min`, `max`, `active`, `location_id`) VALUES ('2', '8', '30', '1', '1');
INSERT INTO `sensor` (`id`, `min`, `max`, `active`, `location_id`) VALUES ('3', '20', '25', '0', '1');

-- HUMIDITY SENSOR
INSERT INTO `humidity_sensor` (`id`, `absolute`, `relative`) VALUES ('1', '12', '13');

-- LIGHT SENSOR
INSERT INTO `light_sensor` (`id`, `radiometry`, `luminous`) VALUES ('2', '19', '20');

-- TEMP SENSOR
INSERT INTO `temp_sensor` (`id`, `temp`) VALUES ('3', '17');
