package co.com.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class NetC2ServiceException extends Exception {
	
	private static final long serialVersionUID = 1432703797276748430L;
	
	private HttpStatus status;

	public NetC2ServiceException(String message, Throwable cause) {
	    super(message, cause);
	}

	public NetC2ServiceException(String message, String field, HttpStatus conflict) {
	   super(message);
	}

	public NetC2ServiceException(Throwable cause) {
	   super(cause);
	}

	public NetC2ServiceException(String message, HttpStatus httpStatus) {
		super(message);
		status = httpStatus;
	}
	
}
