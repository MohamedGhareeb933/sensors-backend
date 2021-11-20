package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.assembler.EnvironmentModelAssembler;
import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dto.EnvironmentModel;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.excpetion.SensorEntitiesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseEntity<EnvironmentModel> save(Environment payload) {

        EnvironmentModel environment = assembler.toModel(environmentRepository.save(payload));

        return ResponseEntity.created(environment.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(environment);
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
                }).get();

        EnvironmentModel entityEnvironment = assembler.toModel(persistEnvironment);

        return ResponseEntity.created(entityEnvironment.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityEnvironment);
    }


}
