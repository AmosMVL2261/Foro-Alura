package com.alura.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alura.repositories.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService {
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public AutenticacionService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	// La autenticación sera a traves del usuarioRepository, usando el email como el username
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(email);
	}

}
