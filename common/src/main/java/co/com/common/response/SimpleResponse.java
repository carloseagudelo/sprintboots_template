package co.com.common.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class SimpleResponse implements IResponse {

	private Object data;
	
	@SerializedName("status")
	private HttpStatus status;
	
	public SimpleResponse(Object data, HttpStatus statusCode) {
		this.data = data;
		this.status = statusCode;
	}
	
	public SimpleResponse(Object data, HttpStatus statusCode,int page) {
		this.data = data;
		this.status = statusCode;
	}
	
	@Override
	public ResponseEntity<String> toResponseEntity() {
		return new ResponseEntity<String>(this.toJson(), ResponseHeader.RESPONSE_HEADERS, this.getHttpStatus());
	}

	@Override
	public String toJson() {
		Gson parser = new Gson();
		return parser.toJson(this);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return status;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public Object getResult() {
		return data;
	}

	public void setResult(Object data) {
		this.data = data;
	}	

	public static class SimpleResponseBuilder {
		private HttpStatus status;
		private Object data;
		
		public SimpleResponseBuilder status(HttpStatus status) {
			this.status = status;
			return this;
		}

		public SimpleResponseBuilder result(Object data) {
			this.data = data;
			return this;
		}

		public SimpleResponseBuilder responseHeaders(HttpHeaders responseHeaders ) {
			ResponseHeader.RESPONSE_HEADERS = responseHeaders;
			return this;
		}

		public SimpleResponse build() {
			SimpleResponse respuesta = new SimpleResponse(data, status);
			return respuesta;
		}
	}
	


}
