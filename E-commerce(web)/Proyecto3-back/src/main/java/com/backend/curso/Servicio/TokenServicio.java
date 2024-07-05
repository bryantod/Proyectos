package com.backend.curso.Servicio;

import com.backend.curso.Configuracion.MessageStrings;
import com.backend.curso.Entidades.TokenAutenticacion;
import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Excepciones.AuthenticationFailException;
import com.backend.curso.Repositorio.TokenRepositorio;
import com.backend.curso.Utilidades.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class TokenServicio {

    @Autowired
    TokenRepositorio tokenRepositorio;

    public void guardarconfirmacionToken(TokenAutenticacion tokenAutenticacion) {
        tokenRepositorio.save(tokenAutenticacion);
    }

    public TokenAutenticacion getToken(Usuarios usuario) {
        return tokenRepositorio.findTokenByUsuario(usuario);
    }

    public Usuarios getUsuario(String token) {
        TokenAutenticacion authenticationToken = tokenRepositorio.findTokenByToken(token);
        if (authenticationToken != null) {
            Usuarios usuario = authenticationToken.getUsuario();
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario.getNombre());
                return usuario;
            }
            System.out.println("Usuario no encontrado");
        } else {
            System.out.println("Token no valido.");
        }
        return null;
    }

    public void autenticar(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUsuario(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }
}