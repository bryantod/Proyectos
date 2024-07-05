package com.backend.curso.Repositorio;

import com.backend.curso.Entidades.Carrito;
import com.backend.curso.Entidades.Cursos;
import com.backend.curso.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepositorio extends JpaRepository<Carrito, Integer> {

    Optional<Carrito> findByCursosAndUsuarios(Cursos cursos, Usuarios usuarios);
    List<Carrito> findAllByUsuariosOrderByFechacreadaDesc(Usuarios usuarios);
}
