package com.alura.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	private SecurityFilter securityFilter;
	
	@Autowired
	public SecurityConfigurations(SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;
	}
	
	// Cambiamos la politica de seguridad de statefull a stateless
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Le indicamos a spring el tipo de sesion
			.and().authorizeHttpRequests() // request sin necesidad de autenticacion
			.requestMatchers(HttpMethod.POST, "/login").permitAll()
			.requestMatchers("/swagger-ui.html", "/v3/api-docs", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
			.anyRequest().authenticated() // todos los demas request requieren autenticacion
			.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Comentario 1
			.build();
	}
	
	/*
	 * Comentario 1
	 * Colocamos esto para ejecutar nuestro filtro antes que el de spring, para que los request puedan ser 
	 * resueltos y no den un error 403. Esto verificara que el usuario exista y sea valido, sin embargo
	 * esto genera que se requiera un inicio de sesion statefull, por lo que en security filter forzamos un inicio
	 * de sesion stateless
	 */
	
	// Variable necesaria para la verificacion de la autenticación del usuario
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// Algoritmo que se le indica a spring usar para la encriptación de la contraseña recibida
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
