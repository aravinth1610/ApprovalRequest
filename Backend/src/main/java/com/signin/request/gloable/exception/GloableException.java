package com.signin.request.gloable.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.signin.request.payload.response.ErrorResponse;

@RestControllerAdvice
public class GloableException {

	private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message,
			String validationErrors) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(status);
		errorResponse.setStatusCode(status.value());
		errorResponse.setMessgae(message);
		errorResponse.setReason(status.getReasonPhrase());
		errorResponse.setValidationErrors(validationErrors);
		errorResponse.setTimestamp(new Date());
		return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		ResponseEntity<ErrorResponse> errorResponseResponseEntity = buildErrorResponse(HttpStatus.BAD_REQUEST,
				"Validation Error", fieldErrors.get(0).getField() + " " + fieldErrors.get(0).getDefaultMessage());

		return errorResponseResponseEntity;

	}

}
