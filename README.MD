# SENSOR PROJECT 

created with Java and spring 

## build - install 

assuming you have java and maven installed globally in your computer and also MySQL 

open `db-scripts` file and run the following scripts 

first: **create-user.sql**

second : **create-schema-table.sql**  

third : **predefined-data.sql**

## run 

open teminal and `cd` to sensor-backend file then run -> `mvn spring-boot:run`

or from your IDE navigate to `sensors-spring\src\main\java\ghareeb\sensors\`  `spring\SensorsSpringApplication.java` and `run`

## development server 

after you build and run the project

head to http://localhost:8080/


## SQL Scripts Has : 

 - create-user.sql 
    - creating my SQL connection with username as *user* and password as *user*

        you can change mysql connection user name and password but that will require you to change the user and  password of the spring properties file too 

 - create-schema-tables.sql
    - the script create a schema for the spring datasource and table for the entities 

 - predefined-data.sql 
    - has a predefined data for Environment, location and sensors. 
 


# SENSORS TABLES IN THE DATABASE`

Sensor has all the main columns like min, max and active 

temp,humidity,light sensor that has its specific attribute like temperature 

each child table primary key is also a foreign key to the parent sensor table 

# JAVA inheritance 

entities use JOINED inheritance

that means the optimization wouldn't be great but we will have JSON Deserialization to polymorphic types

For example: in the Location entity we have one to many relation with the sensor 

it wouldn't be great if we initialized humidity sensor and Light and Temp and we may have new types of sensor in the future 

not just that,  we will also initialize their relation and mapping for each one 

instead, we have in location entity a Sensor relation

and Sensor is the parent class to each child sensor 

# GET  

USE CLIENT YOU PREFER LIKE POSTMAN 

GET : "https://localhost:8080/evnrionments" 

to get all the environments 

GET : "https://localhost:8080/evnrionment/1" 

to get invironment id 


GET : "https://localhost:8080/{entity}"

Entity : evnrionment, location, sensor

# POST 

POST: https://localhost:8080/add  

OPEN `JSON/POST` folder  and copy the .json payloads

# PUT 

PUT : https://localhost:8080/humidity/1

OPEN `JSON/PUT` folder and copy the .json payloads

# DELETE 

**NOTE** THAT ENVIRONEMNT HAS CASCADE ALL TO LOCATION AND LOCATION HAS CASCADE ALL TO SENSOR 
THAT MEANS IF YOU DELETE ENVIRONEMNT THAT WILL DELETE THE LOCATION AND THE LOCATION SENSORS ASWELL

DELETE : "localhost:8080/environment/1

delete environment and it's associate 

DELETE "localhost:8080/location/1

delete location environment and all it's associate. 

