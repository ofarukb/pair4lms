package com.tobeto.java4a.pair4lms.core.configurations;

import com.tobeto.java4a.pair4lms.core.utils.exceptions.problemdetails.AuthenticationProblemDetails;
import com.tobeto.java4a.pair4lms.core.utils.exceptions.problemdetails.BusinessProblemDetails;
import com.tobeto.java4a.pair4lms.core.utils.exceptions.problemdetails.ValidationProblemDetails;
import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;

import io.jsonwebtoken.JwtException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ BusinessException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BusinessProblemDetails handleRuntimeException(BusinessException exception) {
		return new BusinessProblemDetails(exception.getMessage());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
		List<String> errorMessages = new ArrayList<>();
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		for (FieldError error : errors) {
			errorMessages.add(error.getDefaultMessage());
		}
		return new ValidationProblemDetails(errorMessages);
	}

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthenticationProblemDetails handleBadCredentialsException(BadCredentialsException exception) {
        return new AuthenticationProblemDetails(exception.getMessage());
    }

	@ExceptionHandler({ JwtException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public AuthenticationProblemDetails handleJwtException(JwtException exception) {
		return new AuthenticationProblemDetails(exception.getMessage());
	}
}
