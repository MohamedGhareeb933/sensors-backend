package ghareeb.sensors.spring.controller;

import ghareeb.sensors.spring.dto.EnvironmentModel;
import ghareeb.sensors.spring.entity.Environment;
import ghareeb.sensors.spring.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/environments")
public class EnvironmentRestController  implements RestControllerHandler<EnvironmentModel, Environment> {

     @Autowired
     @Qualifier("environmentService")
     private RestService<EnvironmentModel, Environment> environmentService;

    @GetMapping("/{id}")
    public ResponseEntity<EnvironmentModel> find(@PathVariable Long id) {
       return environmentService.find(id);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EnvironmentModel>> findAll() {
        return environmentService.findAll();
    }


    @PostMapping
    public ResponseEntity<EnvironmentModel> save(@RequestBody Environment payload) {
        return environmentService.save(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvironmentModel> update(@RequestBody Environment payload, @PathVariable Long id) {
        return environmentService.update(payload, id);
    }

}
