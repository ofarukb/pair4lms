package com.tobeto.java4a.pair4lms.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.tobeto.java4a.pair4lms.entities.Role;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final UserService userService;

	private static final String[] WHITE_LIST_URLS = { "/swagger-ui/**", "/v3/api-docs", "/api/v1/auth/**" };

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userService);

		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URLS).permitAll()
						.requestMatchers(HttpMethod.GET).permitAll()
						.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
						.requestMatchers(HttpMethod.PUT).hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers(HttpMethod.DELETE).hasAnyAuthority(Role.ADMIN.name())
						.requestMatchers(HttpMethod.POST).hasAnyAuthority(Role.ADMIN.name())
						.anyRequest().permitAll())
				.httpBasic(AbstractHttpConfigurer::disable);

		return http.build();
	}
}
