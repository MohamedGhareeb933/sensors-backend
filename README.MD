# EARLY REVISION 

the project is not yet ended and does not have code documentation yet 

# TABLES
 Sensor has all the main columns like min, max and active 

and temp sensor that has its specific attribute like temperature 

and temp table primary key is also a foreign key to the sensor table 

# JAVA PROJECT 

entities use JOINED inheritance

that means the optimization wouldn't be great but we will have JSON Deserialization to polymorphic types

For example: in the Location entity we have one to many relation with the sensor 

it wouldn't be great if we initialized humidity sensor and Light and Temp and we may have new types of sensor in the future 

not just that,  we will also initialize their relation and mapping each one 

instead, we have in location entity a Sensor relation

and Sensor is the parent class to each child sensor 
