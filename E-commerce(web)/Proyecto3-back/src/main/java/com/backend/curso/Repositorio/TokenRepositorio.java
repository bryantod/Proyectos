package com.backend.curso.Repositorio;

import com.backend.curso.Entidades.TokenAutenticacion;
import com.backend.curso.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepositorio extends JpaRepository<TokenAutenticacion, Integer> {
    TokenAutenticacion findTokenByUsuario(Usuarios usuario);
    TokenAutenticacion findTokenByToken(String token);
}
