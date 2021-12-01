package ghareeb.sensors.spring.service;

import ghareeb.sensors.spring.assembler.LocationModelAssembler;
import ghareeb.sensors.spring.dao.EnvironmentRepository;
import ghareeb.sensors.spring.dao.LocationRepository;
import ghareeb.sensors.spring.model.LocationModel;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("locationService")
public class LocationService  implements RestService<LocationModel, Location>  {

    @Autowired
    private LocationModelAssembler assembler;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public ResponseEntity<LocationModel> find(Long id) {
        return new ResponseEntity<>(assembler.toModel(locationRepository.findById(id).orElseThrow( () ->
                        new EntityNotFoundException(String.format("could not found location with id:%s", id)))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CollectionModel<LocationModel>> findAll() {
        return new ResponseEntity<>(assembler.toCollectionModel(locationRepository.findAll()),
                HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<LocationModel> save(Location payload) {
        
        Environment persistEnvironment = findEnvironment(payload);

        persistEnvironment.add(payload);

        LocationModel location = assembler.toModel(locationRepository.save(payload));
        return ResponseEntity.created(location.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(location);
    }
    
    @Override
    public ResponseEntity<LocationModel> update(Location payload, Long id) {

        Location persistLocation = findLocation(payload, id);

        LocationModel locationModel = assembler.toModel(persistLocation);

        return new ResponseEntity<>(locationModel, HttpStatus.CREATED);
    }

    private Location findLocation(Location payload, Long id) {
        return locationRepository.findById(id).map(
                location -> {
                    if (payload.getName() != null)
                        location.setName(payload.getName());

                    if (payload.isAbnormalTemperature() != null)
                        location.setAbnormalTemperature(payload.isAbnormalTemperature());

                    if (payload.isAbnormalHumidity() != null)
                        location.setAbnormalHumidity(payload.isAbnormalHumidity());

                    if (payload.isAbnormalLight() != null)
                        location.setAbnormalLight(payload.isAbnormalLight());

                    return locationRepository.save(location);
                }
        ).orElseThrow(() -> new EntityNotFoundException(String.format("could not update the entity")));
    }

    // find Environment property by id and add location to environment if the location is added by itself
    private Environment findEnvironment(Location payload) {

        Long id = payload.getEnvironment().getId();
        return environmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Environment with id:%s is not found or might not be exist", id)));
    }

}
