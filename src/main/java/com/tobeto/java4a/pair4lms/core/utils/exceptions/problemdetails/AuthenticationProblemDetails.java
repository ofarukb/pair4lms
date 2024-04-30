package com.tobeto.java4a.pair4lms.core.utils.exceptions.problemdetails;

public class AuthenticationProblemDetails extends ProblemDetails {

	public AuthenticationProblemDetails(String detail) {
		setDetail(detail);
        setTitle("Authentication Violation");
        setType("AuthenticationException");
	}
}
