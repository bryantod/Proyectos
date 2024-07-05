package com.backend.curso.Repositorio;

import com.backend.curso.Entidades.ListaDeseos;
import com.backend.curso.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer> {
    Usuarios findByCorreo(String correo);
}
