package com.backend.curso.Servicio;

import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.dto.usuario.DatosUsuarioDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServicio {

    @SneakyThrows
    public DatosUsuarioDTO obtenerDatosUsuario(Usuarios usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario null");
        }
        return new DatosUsuarioDTO(usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getImg());
    }
}
