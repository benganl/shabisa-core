package za.co.shabisa.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShabisaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private List<String> errors;

    public ShabisaException(String message) {
	super(message);
	this.errors = new ArrayList<>();
    }

    public ShabisaException(Throwable cause) {
	super(cause);
    }

    public ShabisaException(String message, Throwable cause) {
	super(message, cause);
	this.errors = new ArrayList<>();
    }

    public ShabisaException(String message, List<String> errors) {
	super(message);
	this.errors = errors;
    }
    public ShabisaException(String message, Throwable cause, List<String> errors) {
	super(message, cause);
	this.errors = errors;
    }
}
