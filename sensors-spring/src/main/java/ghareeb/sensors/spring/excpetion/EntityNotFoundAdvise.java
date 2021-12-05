package ghareeb.sensors.spring.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * EntityNotFoundAdvise
 * has Exception Handler of SensorEntitiesNotFoundException class
 * has NotFound Method that return ResponseEntity of EntityNotFoundResponse that has
 * message and time and status of Http
 *
 * return NotFound Response when throw
 */
@ControllerAdvice
public class EntityNotFoundAdvise {

    @ExceptionHandler(SensorEntitiesNotFoundException.class)
    ResponseEntity<EntityNotFoundResponse> notFound(SensorEntitiesNotFoundException enf) {

       EntityNotFoundResponse response = new EntityNotFoundResponse(
               enf.getMessage(),
               HttpStatus.NOT_FOUND.value(),
               System.currentTimeMillis()
       );

       return new ResponseEntity<EntityNotFoundResponse>(response, HttpStatus.NOT_FOUND);
    }

}
