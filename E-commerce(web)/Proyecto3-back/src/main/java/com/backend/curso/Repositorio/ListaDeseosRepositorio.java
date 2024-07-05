package com.backend.curso.Repositorio;

import com.backend.curso.Entidades.ListaDeseos;
import com.backend.curso.Entidades.Usuarios;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ListaDeseosRepositorio extends JpaRepository<ListaDeseos,Integer> {
    List<ListaDeseos> findAllByusuariosOrderByCursos(Usuarios usuarios);
}
