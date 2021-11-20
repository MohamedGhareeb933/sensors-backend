package ghareeb.sensors.spring.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestService<T, D> {

    ResponseEntity<T> find(Long id);

    ResponseEntity<CollectionModel<T>> findAll();

    ResponseEntity<T> save(D payload);

    ResponseEntity<T> update(D payload, Long id);
}
