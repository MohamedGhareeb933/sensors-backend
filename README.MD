# SENSOR PROJECT 

Java and Spring project 

## build - install 

assuming you have java and maven installed globally in your computer and also MySQL 

open `db-scripts` file and run the following scripts 

first: **create-user.sql**

second : **create-schema-table.sql**  

third : **predefined-data.sql**

## run 

`Note: you may need Lombok Extension or Plugin`


open terminal and `cd` to sensor-backend file then run -> 

```bash
`mvn spring-boot:run`
```

or from your IDE navigate to : 

`sensors-spring\src\main\java\ghareeb\sensors\`  `spring\SensorsSpringApplication.java` and `run`

## development server 

after you build and run the project

head to http://localhost:8080/


## SQL Scripts Has : 

 - create-user.sql 
    - creating my SQL connection with username as *user* and password as *user*

        you can change MySQL connection user name and password but that will require you to change the user and  password of the spring properties file too 

 - create-schema-tables.sql
    - the script create a schema for the spring datasource and table for the entities 

 - predefined-data.sql 
    - has predefined data for Environment, location, and sensors. 
 


# SENSORS TABLES IN THE DATABASE`

Sensor Entity has all the main columns like min, max and active 

temp, humidity, light sensor that has its specific attribute like temperature 

each child table primary key is also a foreign key to the parent sensor table 

# JAVA inheritance 

entities use JOINED inheritance

that means the optimization wouldn't be great but we will have JSON Deserialization to polymorphic types

For example: in the Location entity we have one-to-many relation with the sensor 

it wouldn't be great if we initialized humidity sensor and Light and Temp and we may have new types of sensor in the future 

not just that,  we will also initialize their relation and mapping for each one 

instead, we have in location entity a Sensor relation

and Sensor is the parent class to each child sensor 

# SPRING HATEOAS

 `Entity Models and Assemblers`

 ```java
public class EnvironmentModel extends RepresentationModel<EnvironmentModel> {
 ```

 this entity model extends RepresentationModel to get EntityModel with links in response. 

 it has all the properties of the entity 

```java
public class EnvironmentModelAssembler extends RepresentationModelAssemblerSupport<Environment, EnvironmentModel> {
```
this class is responsible for mapping Entity to EntityModel. 

and add links to the entity model 

and it has 3 methods 

`toModel` which call the toModel mapper and add links to the Model 

`toCollectionModel` mapping collection of Entity 

`toLocationModel` accept collection of Entity and return Collection of EntityModel

```java 
public class EnvironmentToModel {
```

this class the Mapper for the Entity to Entity Model 

first, it calls the EntityModel Builder 

then map each property for the Entity to Entity Model 

it has method overloading to avoid nulls in the parameters 


# Controller and Service Layers 

Class of type Implement Interface that has type parameters 

two types of generics 

first: generic type parameter is the EntityModel 

second: generic Type Parameter is the Entity.

```java
public class EnvironmentRestController  implements RestControllerHandler<EnvironmentModel, Environment> {
```

# Excpetion

Exception has 3 Properties 

Message: which is thrown, 

Time and HTTP status 

the exception is thrown HttpStatus of not found 

in case of Cannot get Persisted Entity or update and save. 

with message Attached to it 

```java
return new ResponseEntity<EntityNotFoundResponse>(response, HttpStatus.NOT_FOUND);
```


# GET  

USE CLIENT YOU PREFER LIKE POSTMAN 

GET : "https://localhost:8080/evnrionments"

to get all the environments 

GET : "https://localhost:8080/evnrionments/1" 

to get environment id 


GET : "https://localhost:8080/{entity}"

Entity: environments, locations, sensors

# POST 

POST: https://localhost:8080/environemnts

OPEN JSON

OPEN `JSON/POST` folder  and copy the .json payloads

# PUT 

PUT : https://localhost:8080/sensors/1

OPEN `JSON/PUT` folder and copy the .json payloads

# DELETE 

**NOTE** THAT ENVIRONMENT HAS CASCADE ALL TO LOCATION AND LOCATION CASCADE ALL TO SENSOR 
THAT MEANS IF YOU DELETE ENVIRONMENT THAT WILL DELETE THE LOCATION AND THE LOCATION SENSORS ASWELL

DELETE : "localhost:8080/environments/1

delete environment and its associate 

DELETE "localhost:8080/location/1

delete location environment and all its associates. 

