package co.com.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface IResponsePage {
	public ResponseEntity<String> toResponseEntity();
	public String toJson();
	public HttpStatus getHttpStatus();
	public int page(int page);
}
