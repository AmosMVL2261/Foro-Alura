package com.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
