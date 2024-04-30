package com.tobeto.java4a.pair4lms.core.filters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.tobeto.java4a.pair4lms.core.services.JwtService;
import com.tobeto.java4a.pair4lms.entities.Role;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final HandlerExceptionResolver exceptionResolver;

	public JwtFilter(JwtService jwtService, UserService userService,
			@Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
		this.jwtService = jwtService;
		this.exceptionResolver = handlerExceptionResolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtHeader = request.getHeader("Authorization");

		if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
			String jwt = jwtHeader.substring(7);
			try {
				jwtService.validateToken(jwt);
				String username = jwtService.extractUsername(jwt);
				int userId = jwtService.extractUserId(jwt);
				List<Role> roles = jwtService.extractRoles(jwt).stream().map((roleStr) -> Role.valueOf(roleStr))
						.collect(Collectors.toList());

				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, userId,
						roles);
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			} catch (JwtException e) {
				exceptionResolver.resolveException(request, response, null, e);
			}
		}
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
		}
	}
}
