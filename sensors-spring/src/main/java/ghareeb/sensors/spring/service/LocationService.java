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
import java.util.Arrays;


/**
 * Location Service Layer
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
    public ResponseEntity<CollectionModel<LocationModel>> save(Location payload) {

        if (payload.getEnvironment() != null) {

            Environment persistEnvironment = findEnvironment(payload);

            persistEnvironment.add(payload);
        }

        locationRepository.save(payload);

        CollectionModel collectionModel =  assembler.toCollectionModel(Arrays.asList(payload));

        return new ResponseEntity<>(collectionModel, HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<LocationModel> update(Location payload, Long id) {

        Location persistLocation = findLocation(payload, id);

        LocationModel locationModel = assembler.toModel(persistLocation);

        return new ResponseEntity<>(locationModel, HttpStatus.ACCEPTED);
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
