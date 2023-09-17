package com.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	UserDetails findByEmail(String email);

}
