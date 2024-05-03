package com.tobeto.java4a.pair4lms.core.configurations;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.tobeto.java4a.pair4lms.core.utils.JsonBuilder;
import com.tobeto.java4a.pair4lms.core.utils.exceptions.problemdetails.AuthenticationProblemDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	private final JsonBuilder jsonBuilder;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter()
				.write(jsonBuilder.serialize(new AuthenticationProblemDetails(accessDeniedException.getMessage())));
	}
}
