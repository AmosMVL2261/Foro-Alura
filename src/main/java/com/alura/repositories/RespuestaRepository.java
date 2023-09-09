package com.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

}
