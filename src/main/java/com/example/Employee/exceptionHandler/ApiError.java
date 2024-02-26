package com.example.Employee.exceptionHandler;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	private HttpStatus httpStatus;
	String error;
	
	
	public ApiError(HttpStatus badRequest, String error) {
		this.httpStatus= badRequest;
		this.error = error;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}
	

}
