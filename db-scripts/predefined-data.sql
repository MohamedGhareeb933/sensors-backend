-- PREDEFINED DATA 


-- ENVIROMENT 
INSERT INTO `sensors`.`environment` (`id`, `name`, `alarm`, `email`) VALUES ('1', 'ELSAID', '0', 'mohamed@gmail.com');

-- LOCATION 
INSERT INTO `sensors`.`location` (`id`, `name`, `ab_temp`, `ab_light`, `ab_humidity`, `environment_id`) VALUES ('1', 'salon', '0', '0', '0', '1');

-- HUMIDITY
INSERT INTO `sensors`.`humidity_sensor` (`id`, `absolute`, `relative`, `min`, `max`, `active`, `location_id`) VALUES ('1', '12', '13', '10', '20', '1', '1');

-- LIGHT SENSOR
INSERT INTO `sensors`.`light_sensor` (`id`, `radiometry`, `luminous`, `min`, `max`, `active`, `location_id`) VALUES ('1', '12', '13', '10', '20', '1', '1');

-- TEMP SENSOR
INSERT INTO `sensors`.`temp_sensor` (`id`, `temp`, `min`, `max`, `active`, `location_id`) VALUES ('1', '14', '18', '10', '20', '1');
