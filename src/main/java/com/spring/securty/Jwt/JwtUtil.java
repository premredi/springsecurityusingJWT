package com.spring.securty.Jwt;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	
	public String generateToken(String subject) {
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("antra")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15) ))
				.signWith(SignatureAlgorithm.HS512,secret.getBytes())
				.compact()
				
				;
	}
	public Claims getclaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody()
				;
	}
	public Date getexpdate(String token) {
		return getclaims(token).getExpiration();
	}
	
	public String getusername(String token) {
		return getclaims(token).getSubject();
	}
	public boolean istokenexpired(String token) {
		Date dateexp=getexpdate(token);
		return dateexp.before(new Date(System.currentTimeMillis()));
	}
	public boolean validatetoken(String token,String username) {
		String usernametoken=getusername(token);
		return (username.equals(usernametoken) && !istokenexpired(token));
	}
}
