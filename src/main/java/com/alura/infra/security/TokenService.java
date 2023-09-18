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
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;

	// Genera el token JTW que el usuario utilizara al realizar sus peticiones
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
	
	// Funcion usada para generar la fecha de expiración del token
	private Instant generarFechaExpiracion() {
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-06:00"));
	}
	
	// Obtenemos el usuario del token necesario para la validacion del token recibido
	public String getSubject(String token) {
		if(token == null) {
			throw new RuntimeException("Token no es valido");
		}
		DecodedJWT decodedJWT;
		try {
			
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validando la firma del token
		    JWTVerifier verifier = JWT.require(algorithm)
		        // specify an specific claim validations
		        .withIssuer("foroAlura") // Verificar que el emisor sea foroAlura
		        // reusable verifier instance
		        .build();
		        
		    decodedJWT = verifier.verify(token);
		    if(decodedJWT.getSubject() == null) {
				throw new RuntimeException("Verifier invalido");
			}
			return decodedJWT.getSubject();
			
		} catch (JWTVerificationException exception){
		    // Invalid signature/claims
			throw new RuntimeException(exception);
		}
		
	}
}
