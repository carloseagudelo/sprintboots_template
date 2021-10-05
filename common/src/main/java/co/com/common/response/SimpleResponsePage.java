package co.com.common.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class SimpleResponsePage implements IResponsePage {
	
	private Object data;
	
	@SerializedName("status")
	private HttpStatus status;
	private int page;
	
	
	public SimpleResponsePage(Object data, HttpStatus statusCode,int page) {
		this.data = data;
		this.status = statusCode;
		this.page = page;
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

	public int page(int page) {
		return this.page = page;
	}
	


	public static class SimpleResponseBuilder {
		private HttpStatus status;
		private Object data;
		private int page;
		
		public SimpleResponseBuilder page(int page){
			this.page = page;
			return this;
		}
		

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

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

		public SimpleResponsePage build() {
			SimpleResponsePage respuesta = new SimpleResponsePage(data, status,page);
			return respuesta;
		}
	}
	
}
