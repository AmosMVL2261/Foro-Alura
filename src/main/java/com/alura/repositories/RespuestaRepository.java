package com.alura.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
	//Find all topico's respuestas packed in a Page object for pagination
	Page<Respuesta> findAllByTopicoId(Integer id, Pageable pageable);

}
