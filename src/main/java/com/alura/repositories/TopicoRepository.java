package com.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer>{

}
