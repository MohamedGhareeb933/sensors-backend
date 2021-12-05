package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.assembler.SensorsModelAssembler;
import ghareeb.sensors.spring.controller.SensorRestController;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.dao.SensorRepository;
import ghareeb.sensors.spring.deprecated.dto.Payload;
import ghareeb.sensors.spring.entity.*;
import ghareeb.sensors.spring.excpetion.SensorEntitiesNotFoundException;
import ghareeb.sensors.spring.model.SensorsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * Sensor Service Layer
 *
 * has for http Methods find/all Save and update
 *
 * find Method is : finding Persist Entity and Map it toModel then map to Response Entity.
 *
 * find all method is : finding all persist Entity and put them to CollectionModel List
 * then Map to Repose Entity
 *
 * save Method is : check if each of subclass is exists in the payload
 * if It's there then add it to location then save it
 * and then add it to SensorModel list
 * finally return Response Entity of CollectionModel of http status.
 *
 * update method is : check for every Property in the payload and find persist and if the entity
 * in persist is instance of subclass.
 * update persist class from payload and return ReponseEntity of SensorModel wth Httpstatus.
 */

@Service("sensorService")
public class SensorsService implements RestService<SensorsModel, Payload>  {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorsModelAssembler assembler;

    @Override
    public ResponseEntity<SensorsModel> find(Long id) {

            Optional sensor =  sensorRepository.findById(id);

            if (!sensor.isPresent())
                throw new SensorEntitiesNotFoundException(String.format("sensor with id %s is not found", id));

            SensorsModel sensorsModel = assembler.toModel((Sensor) sensor.get());

            return new ResponseEntity<>(sensorsModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CollectionModel<SensorsModel>> findAll() {

         List<Sensor> sensors = sensorRepository.findAll();
         CollectionModel<SensorsModel> sensorsModels = assembler.toCollectionModel(sensors);

         return new ResponseEntity<>(sensorsModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CollectionModel<SensorsModel>> save(Payload payload) {

        List<SensorsModel> sensorsModelList = new ArrayList<>();

        Optional<Sensor> sensor = Optional.empty();

        if (payload.getHumiditySensor() != null) {

            sensor = Optional.of((HumiditySensor) payload.getHumiditySensor());

            if (sensor.get().getLocation() != null) {
                Location location = findLocation(sensor.get());
                location.add(sensor.get());
            }

            HumiditySensor humiditySensor = (HumiditySensor) sensorRepository.save(sensor.get());

            SensorsModel sensorsModel = assembler.toModel(humiditySensor);

            sensorsModelList.add(sensorsModel);

        }
        if (payload.getLightSensor() != null) {
            sensor = Optional.of((LightSensor) payload.getLightSensor());

            if (sensor.get().getLocation() != null) {
                Location location = findLocation(sensor.get());
                location.add(sensor.get());
            }
            LightSensor lightSensor = (LightSensor) sensorRepository.save(sensor.get());

            SensorsModel sensorsModel = assembler.toModel(lightSensor);

            sensorsModelList.add(sensorsModel);


        }
        if (payload.getTempSensor() != null){
            sensor = Optional.of((TempSensor) payload.getTempSensor());

            if (sensor.get().getLocation() != null) {
                Location location = findLocation(sensor.get());
                location.add(sensor.get());
            }

            TempSensor tempSensor = (TempSensor) sensorRepository.save(sensor.get());

            SensorsModel sensorsModel = assembler.toModel(tempSensor);

            sensorsModelList.add(sensorsModel);

        }

        if (!sensor.isPresent() || sensorsModelList.isEmpty()) {
            throw new SensorEntitiesNotFoundException("Cannot Map Sensor to subclass");
        }

        CollectionModel collectionModel = CollectionModel.of(sensorsModelList,
                linkTo(methodOn(SensorRestController.class).findAll()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SensorsModel> update(Payload payload, Long id) {

        Optional<Sensor> persistSensor = sensorRepository.findById(id).map(
                sensor -> {
                    if (sensor instanceof HumiditySensor) {

                        HumiditySensor fromPersist = (HumiditySensor) sensor;
                        HumiditySensor fromPayload = payload.getHumiditySensor();

                        updateSensor(payload.getHumiditySensor(), fromPersist);

                        if (fromPayload.getRelative() != null)
                            fromPersist.setRelative(fromPayload.getRelative());

                        if (fromPayload.getAbsolute() != null)
                            fromPersist.setAbsolute(fromPayload.getAbsolute());

                        return sensorRepository.save(fromPersist);

                    } else if (sensor instanceof LightSensor) {

                        LightSensor fromPersist = (LightSensor) sensor;
                        LightSensor fromPayload = payload.getLightSensor();

                        updateSensor(fromPayload, fromPersist);

                        if (fromPayload.getLuminous() != null)
                            fromPersist.setLuminous(fromPayload.getLuminous());

                        if (fromPayload.getRadiometry() != null)
                            fromPersist.setRadiometry(fromPayload.getRadiometry());

                        return sensorRepository.save(fromPersist);

                    } else if (sensor instanceof TempSensor) {

                        TempSensor fromPersist = (TempSensor) sensor;
                        TempSensor fromPayload = payload.getTempSensor();

                        updateSensor(fromPayload, fromPayload);

                        if (fromPayload != null)
                            fromPersist.setTemp(fromPayload.getTemp());

                        return sensorRepository.save(fromPersist);

                    } else {
                        throw new SensorEntitiesNotFoundException(String.format("sensor in abstract class and can not be saved or updated with out mapping to sub class "));
                    }
                }
        );

        if (!persistSensor.isPresent())
            throw new SensorEntitiesNotFoundException(String.format("can not post or update"));

        SensorsModel sensorsModel = assembler.toModel(persistSensor.get());

        return new ResponseEntity<>(sensorsModel, HttpStatus.ACCEPTED);

    }

    private Location findLocation(Sensor sensor) {
        return locationRepository
                .findById(sensor.getLocation().getId())
                .orElseThrow(
                        () ->  new SensorEntitiesNotFoundException(String.format("sensors location is not found"))
                );
    }

    private void updateSensor(Sensor fromPayload, Sensor fromPersist){

        try {

            if (fromPayload.getMin() != null)
                fromPersist.setMin(fromPayload.getMin());

            if (fromPayload.getMax() != null)
                fromPersist.setMax(fromPayload.getMax());

            if (fromPayload.isActive() != null)
                fromPersist.setActive(fromPayload.isActive());

            if (fromPayload.getLocation() != null)
                fromPersist.setLocation(findLocation(fromPayload));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
