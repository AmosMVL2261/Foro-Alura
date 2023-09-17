package com.alura.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alura.modelo.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;

	public String generarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    String token = JWT.create()
		        .withIssuer("foroAlura")
		        .withSubject(usuario.getEmail())
		        .withClaim("id", usuario.getId())
		        .withExpiresAt(generarFechaExpiracion())
		        .sign(algorithm);
		    
		    return token;
		} catch (JWTCreationException exception){
		    // Invalid Signing configuration / Couldn't convert Claims.
			throw new RuntimeException(exception.getMessage());
		}
	}
	
	private Instant generarFechaExpiracion() {
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-06:00"));
	}
}
