package com.tobeto.java4a.pair4lms.core.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.expiration}")
	private long EXPIRATION;
	
	@Value("${jwt.key}")
	private String SECRET_KEY;

	public String generateToken(String userName, Map<String, Object> extraClaims) {
		String token = Jwts
				.builder()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.claims(extraClaims)
				.subject(userName)
				.signWith(getSignKey())
				.compact();
		return token;
	}
	
	public boolean validateToken(String token) {
		Date expDate = getClaimsFromToken(token).getExpiration();
		return expDate.after(new Date());
	}
	
	public int extractUserId(String token) {
		return (int) getClaimsFromToken(token).get("userId");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> extractRoles(String token) {
		return (List<String>) getClaimsFromToken(token).get("roles");
	}
	
	public String extractUsername(String jwt) {
		return getClaimsFromToken(jwt).getSubject();
	}
	
	private Claims getClaimsFromToken(String token) {
		return Jwts.parser()
				.verifyWith(getSignKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	private SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
