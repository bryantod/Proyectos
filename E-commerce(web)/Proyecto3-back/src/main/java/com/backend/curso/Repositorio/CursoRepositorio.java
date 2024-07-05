package com.backend.curso.Repositorio;

import com.backend.curso.Entidades.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepositorio extends JpaRepository<Cursos, Integer> {
    Optional<Cursos> findById(Integer cursoId);
}
