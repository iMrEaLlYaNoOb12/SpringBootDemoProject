package com.stg.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(value = RationApplicationException.class)
	public ResponseEntity<Map<String, Object>> rationApplicationException(Exception exception) {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "creation Failed");
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("Exception reason", exception.getMessage());
		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);

	}
}
