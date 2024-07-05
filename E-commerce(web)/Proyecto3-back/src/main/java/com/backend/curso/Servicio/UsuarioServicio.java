package com.backend.curso.Servicio;

import com.backend.curso.Entidades.*;
import com.backend.curso.Excepciones.AuthenticationFailException;
import com.backend.curso.Excepciones.CustomException;
import com.backend.curso.Repositorio.UsuarioRepositorio;
import com.backend.curso.dto.RespuestaDto;
import com.backend.curso.dto.usuario.IngresarDto;
import com.backend.curso.dto.usuario.InscribirseDto;
import com.backend.curso.dto.usuario.RespuestaIngresoDto;
import jakarta.xml.bind.DatatypeConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TokenServicio tokenServicio;

    @Transactional
    public RespuestaDto inscribirse(InscribirseDto inscribirseDto) {
        if (Objects.nonNull(usuarioRepositorio.findByCorreo(inscribirseDto.getCorreo()))) {
            try {
                throw new CustomException("Usuario ya inscrito");
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }

        String contrasenasegura = inscribirseDto.getContrasena();
        try {
            contrasenasegura = hashcontrasena(inscribirseDto.getContrasena());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Usuarios usuarios = new Usuarios(inscribirseDto.getNombre(),
                inscribirseDto.getApellido(), inscribirseDto.getCorreo(),
                contrasenasegura);

        // Asignar la URL de la imagen a la entidad Usuarios si está presente
        if (Objects.nonNull(inscribirseDto.getImagen_url())) {
            usuarios.setImg(inscribirseDto.getImagen_url());
        }

        usuarioRepositorio.save(usuarios);

        TokenAutenticacion tokenAutenticacion = new TokenAutenticacion(usuarios);
        tokenServicio.guardarconfirmacionToken(tokenAutenticacion);

        RespuestaDto respuestaDto = new RespuestaDto("Success", "Usuario registrado correctamente");
        return respuestaDto;
    }

    private String hashcontrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(contrasena.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    @SneakyThrows
    public RespuestaIngresoDto ingresar(IngresarDto ingresarDto) throws AuthenticationFailException, CustomException, NoSuchAlgorithmException {

        Usuarios usuarios = usuarioRepositorio.findByCorreo(ingresarDto.getCorreo());
        if (Objects.isNull(usuarios)) {
            throw new AuthenticationFailException("Usuario no valido", "/usuario/ingresar");
        }

        if (!usuarios.getContrasena().equals(hashcontrasena(ingresarDto.getContrasena()))) {
            throw new AuthenticationFailException("Usuario o Contraseña Incorrecta", "/usuario/ingresar");
        }

        TokenAutenticacion token = tokenServicio.getToken(usuarios);

        if (Objects.isNull(token)) {
            throw new CustomException("Token no esta presente");
        }

        return new RespuestaIngresoDto("Verificado", token.getToken());
    }


}
