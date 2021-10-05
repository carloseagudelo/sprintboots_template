package co.com.common.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("finalResponse")
public class FinalResponse {
	public SimpleResponse getResponse(Object data, HttpHeaders responseHeaders, HttpStatus status){
		return new SimpleResponse.SimpleResponseBuilder()
		        .result(data)
		        .responseHeaders(responseHeaders)
		        .status(status)
		        .build();
	}
	public SimpleResponsePage getResponsePage(Object data, HttpHeaders responseHeaders, HttpStatus status,int page){
		return new SimpleResponsePage.SimpleResponseBuilder()
		        .result(data)
		        .responseHeaders(responseHeaders)
		        .page(page)
		        .status(status)
		        .build();
	}
}