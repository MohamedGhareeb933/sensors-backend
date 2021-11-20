package ghareeb.sensors.spring.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestControllerHandler<T, D> {

    ResponseEntity<T> find(Long id);

    ResponseEntity<CollectionModel<T>> findAll();

    ResponseEntity<T> save(D payload);

    ResponseEntity<T> update(D payload, Long id);

}
