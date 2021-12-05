package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.assembler.EnvironmentModelAssembler;
import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.model.EnvironmentModel;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.excpetion.SensorEntitiesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;


/**
 * Environment Service Layer
 *
 * has for http Methods find/all Save and update
 *
 * find Method is : finding Persist Entity and Map it toModel then map to Response Entity.
 *
 * find all method is : finding all persist Entity and put them to CollectionModel List
 * then Map to Repose Entity
 *
 * save Method is : save the Payload and Put it in CollecitonModel list
 * then Map it to Response Entity
 *
 * update method is : check for every Property in the payload and update if the property exist.
 * then map to EntityModel then return ReposnseEntity of ok
 */
@Service("environmentService")
public class EnvironmentService implements RestService<EnvironmentModel, Environment> {

    @Autowired
    private EnvironmentModelAssembler assembler;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public ResponseEntity<EnvironmentModel> find(Long id) {

        return environmentRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new SensorEntitiesNotFoundException(
                                String.format("could not find Environment with id:%s", id )));
          }

    @Override
    public ResponseEntity<CollectionModel<EnvironmentModel>> findAll() {

        List<Environment> environments = environmentRepository.findAll();

        return new ResponseEntity<>(assembler.toCollectionModel(environments), HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<CollectionModel<EnvironmentModel>> save(Environment payload) {

        environmentRepository.save(payload);

        CollectionModel collectionModel = assembler.toCollectionModel(Arrays.asList(payload));

        return new ResponseEntity<>(collectionModel, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<EnvironmentModel> update(Environment payload, Long id) {

        Environment persistEnvironment = environmentRepository.findById(id).map(
                environment -> {
                    if (payload.getEmail() != null) environment.setEmail(payload.getEmail());

                    if (payload.getName() != null) environment.setName(payload.getName());

                    if (payload.isAlarm() != null) environment.setAlarm(payload.isAlarm());

                    if (payload.getLocations() != null) environment.setLocations(payload.getLocations());

                    return environmentRepository.save(environment);
                }).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can not find or update Environment ")));

        EnvironmentModel entityEnvironment = assembler.toModel(persistEnvironment);

        return new ResponseEntity<>(entityEnvironment, HttpStatus.OK);
    }


}
