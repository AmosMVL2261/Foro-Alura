package com.alura.infra.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Clase usada para interceptar el request del usuario para verificar 
 * si el token es valido para habilitar el acceso a los datos
*/
@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Obtener el token del header
		String token = request.getHeader("Authorization");
		if(token == null || token.trim().length() == 0) {
			throw new RuntimeException("El token enviado no es valido");
		}
		token = token.replace("Bearer ", ""); // Borra el prefijo del tipo de token recibido ya que este no se ocupa, solo el token
		//
		
		// Llama al siguiente filtro (hace la correspondiente llamada al controller)
		filterChain.doFilter(request, response);
		
	}

}
