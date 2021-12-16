package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	public RecordNotFoundException() {
		
	}
	
public  RecordNotFoundException(String message) {
	super(message);
}
}