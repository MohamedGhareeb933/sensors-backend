package ghareeb.sensors.spring.excpetion;

public class EntityNotFoundResponse {

    private String message;
    private int status;
    private Long time;

    public EntityNotFoundResponse() {
    }

    public EntityNotFoundResponse(String message, int status, Long time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
