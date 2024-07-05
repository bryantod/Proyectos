package com.backend.curso.Controlador;

import com.backend.curso.Entidades.ListaDeseos;
import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Excepciones.AuthenticationFailException;
import com.backend.curso.Excepciones.CustomException;
import com.backend.curso.Repositorio.UsuarioRepositorio;
import com.backend.curso.Servicio.TokenServicio;
import com.backend.curso.Servicio.UsuarioServicio;
import com.backend.curso.dto.RespuestaDto;
import com.backend.curso.dto.usuario.InscribirseDto;
import com.backend.curso.dto.usuario.RespuestaIngresoDto;
import com.backend.curso.dto.usuario.IngresarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioControl {

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    TokenServicio tokenServicio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/inscribirse")
    public RespuestaDto inscribirse(@RequestBody InscribirseDto inscribirseDto){
        return usuarioServicio.inscribirse(inscribirseDto);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/ingresar")
    public RespuestaIngresoDto ingresar(@RequestBody IngresarDto ingresarDto) throws AuthenticationFailException, CustomException, NoSuchAlgorithmException {
        return usuarioServicio.ingresar(ingresarDto);
    }


}
