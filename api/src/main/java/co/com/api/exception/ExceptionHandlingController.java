package co.com.api.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.common.encrypt.EncryptionException;
import co.com.common.exception.NetC2ServiceException;
import co.com.common.response.FinalResponse;
import co.com.common.response.IResponse;

@ControllerAdvice
public class ExceptionHandlingController {
	
	@Autowired FinalResponse finalResponse; 

	@ExceptionHandler(EncryptionException.class)
    public @ResponseBody ResponseEntity<String> handleEncryptionException(EncryptionException exception) {
		IResponse response;
		HttpHeaders responseHeaders = new HttpHeaders();
		response = finalResponse.getResponse(exception.getMessage(), responseHeaders, HttpStatus.CONFLICT);
		return response.toResponseEntity();
    }

	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
		IResponse response;
		HttpHeaders responseHeaders = new HttpHeaders();
		response = finalResponse.getResponse(exception.getMessage(), responseHeaders, HttpStatus.BAD_REQUEST);
		return response.toResponseEntity();
    }

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<String> handleException(Exception exception) {
		IResponse response;
		HttpHeaders responseHeaders = new HttpHeaders();
        response = finalResponse.getResponse(exception.getMessage(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		return response.toResponseEntity();
    }

	@ExceptionHandler(NetC2ServiceException.class)
	public @ResponseBody ResponseEntity<String> handleNetC2ServiceException(NetC2ServiceException exception) {
		IResponse response;
		HttpHeaders responseHeaders = new HttpHeaders();
		
        HttpStatus status = exception.getStatus() != null? exception.getStatus() : HttpStatus.BAD_REQUEST;
        response = finalResponse.getResponse(exception.getMessage(), responseHeaders, status);
		return response.toResponseEntity();
    }
}
