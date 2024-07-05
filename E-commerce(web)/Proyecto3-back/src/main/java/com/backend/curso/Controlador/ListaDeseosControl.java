package com.backend.curso.Controlador;

import com.backend.curso.Configuracion.ApiResponse;
import com.backend.curso.Entidades.Cursos;
import com.backend.curso.Entidades.ListaDeseos;
import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Excepciones.AuthenticationFailException;
import com.backend.curso.Servicio.ListaDeseosServicio;
import com.backend.curso.Servicio.TokenServicio;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ListaDeseos")
public class ListaDeseosControl {
    @Autowired
    ListaDeseosServicio listaDeseosServicio;
    @Autowired
    TokenServicio tokenServicio;

    @PostMapping("/agregar")
    public ResponseEntity<ApiResponse> añadirListaDeseos(@RequestBody Cursos cursos,
                                                          @RequestParam("Token") String Token) throws AuthenticationFailException {
        tokenServicio.autenticar(Token);

        Usuarios usuarios = tokenServicio.getUsuario(Token);

        ListaDeseos listaDeseos = new ListaDeseos(usuarios, cursos);
        listaDeseosServicio.crearListaDeseos(listaDeseos);

        ApiResponse apiResponse = new ApiResponse(true, "agregado a la lista de deseos");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

   @GetMapping("/{token}")
    public ResponseEntity<List<Cursos>> obtenerListaDeseos(@PathVariable("token") String Token) throws AuthenticationFailException {
        tokenServicio.autenticar(Token);

        Usuarios usuarios = tokenServicio.getUsuario(Token);
        List<Cursos> Cursos = listaDeseosServicio.obtenerListaDeseosPorUsuario(usuarios);
        return new ResponseEntity<>(Cursos, HttpStatus.OK );
    }
    @DeleteMapping("/eliminar/{idCurso}")
    public ResponseEntity<ApiResponse> eliminarDeListaDeseos(
            @RequestParam("Token") String token,
            @PathVariable("idCurso") int idCurso) throws AuthenticationFailException {
        tokenServicio.autenticar(token);

        Usuarios usuario = tokenServicio.getUsuario(token);
        listaDeseosServicio.eliminarDeListaDeseos(usuario, idCurso);

        ApiResponse apiResponse = new ApiResponse(true, "Curso eliminado de tu lista de deseos");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
