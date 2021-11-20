package ghareeb.sensors.spring.excpetion;

public class SensorEntitiesNotFoundException extends RuntimeException{

    public SensorEntitiesNotFoundException(String message) {
        super(message);
    }

    public SensorEntitiesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SensorEntitiesNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SensorEntitiesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
