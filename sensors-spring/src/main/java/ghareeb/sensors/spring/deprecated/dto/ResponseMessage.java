package ghareeb.sensors.spring.deprecated.dto;


/**
 *  the response message we will get after sending http method.
 */
public class ResponseMessage {

    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
