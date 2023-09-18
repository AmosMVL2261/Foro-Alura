package com.alura.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.repositories.UsuarioRepository;

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
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService; 
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Obtener el token del header
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null) {
			String token = authHeader.replace("Bearer ", ""); // Borra el prefijo del tipo de token recibido ya que este no se ocupa, solo el token
			// Verificamos que el usuario tenga una sesión activa llamando al metodo getSUbject 
			// (en esa funcion se hace el proceso de validación, no aquí)
			String subject = tokenService.getSubject(token);
			if(subject != null) {
				//Entonces el token es valido
				UserDetails usuario = usuarioRepository.findByEmail(subject);
				// Forzamos un inicio de sesion
				Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				// Seteamos un inicio de sesion
				SecurityContextHolder.getContext().setAuthentication(authentication); // Comentario 2
			}
		}
		// Llama al siguiente filtro (en este caso al no tener otro filtro hace la correspondiente llamada al controller)
		filterChain.doFilter(request, response);
		
		/*
		 * Comentario 2
		 * Debido a que es un login stateless se requiere forzar el inicio de sesión en cada request, para esto
		 * se usan estas dos lineas de codigo antes descrito
		 */
		
		/*
		// Obtener el token del header
		String token = request.getHeader("Authorization");
		if(token == null || token.trim().length() == 0) {
			throw new RuntimeException("El token enviado no es valido");
		}
		token = token.replace("Bearer ", ""); // Borra el prefijo del tipo de token recibido ya que este no se ocupa, solo el token
		// Verificamos que el usuario tenga una sesión activa llamando al metodo getSUbject 
		// (en esa funcion se hace el proceso de validación, no aquí)
		System.out.println(tokenService.getSubject(token));
		// Llama al siguiente filtro (hace la correspondiente llamada al controller)
		filterChain.doFilter(request, response);
		*/
	}
	
	

}
