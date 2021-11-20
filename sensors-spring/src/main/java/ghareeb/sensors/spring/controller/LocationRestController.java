package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dto.LocationModel;
import ghareeb.sensors.spring.entity.Location;
import ghareeb.sensors.spring.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/locations")
public class LocationRestController implements RestControllerHandler<LocationModel, Location>  {

    @Autowired
    @Qualifier("locationService")
    private RestService<LocationModel, Location> restService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationModel> find(@PathVariable Long id) {
       return restService.find(id);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<LocationModel>> findAll() {
       return restService.findAll();
    }

    @PostMapping
    public ResponseEntity<LocationModel> save(@RequestBody Location payload) {
        return restService.save(payload);
    }

    @PutMapping
    public ResponseEntity<LocationModel> update(@RequestBody Location payload, Long id) {
        return restService.update(payload, id);
    }

}
