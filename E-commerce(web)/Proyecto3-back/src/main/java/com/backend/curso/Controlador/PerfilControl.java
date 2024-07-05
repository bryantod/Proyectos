package com.backend.curso.Controlador;

import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Excepciones.AuthenticationFailException;
import com.backend.curso.Servicio.PerfilServicio;
import com.backend.curso.Servicio.TokenServicio;
import com.backend.curso.dto.usuario.DatosUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Perfil")
public class PerfilControl {
    @Autowired
    TokenServicio tokenServicio;

    @Autowired
    PerfilServicio perfilServicio;

    @GetMapping("/usuario/{token}")
    public ResponseEntity<DatosUsuarioDTO> obtenerDatosUsuario(@PathVariable("token") String token) throws AuthenticationFailException {
        tokenServicio.autenticar(token);
        Usuarios usuario = tokenServicio.getUsuario(token);
        if (usuario == null) {
            throw new AuthenticationFailException("Usuario no encontrado para el token");
        }
        DatosUsuarioDTO datosUsuarioDTO = perfilServicio.obtenerDatosUsuario(usuario);
        return new ResponseEntity<>(datosUsuarioDTO, HttpStatus.OK);
    }
}
