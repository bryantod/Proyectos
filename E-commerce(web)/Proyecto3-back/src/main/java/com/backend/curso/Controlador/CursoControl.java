package com.backend.curso.Controlador;

import com.backend.curso.Entidades.Cursos;
import com.backend.curso.Excepciones.CustomException;
import com.backend.curso.Servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CursoControl {

    private final CursoServicio cursoServicio;

    @Autowired
    public CursoControl(CursoServicio cursoServicio) {
        this.cursoServicio = cursoServicio;
    }
    // Endpoints para cursos
    @GetMapping("/cursos")
    public List<Cursos> obtenerCursos() {
        return cursoServicio.getAllCursos();
    }
    @PostMapping("/cursos/guardar")
    public Cursos guardarCurso(@RequestBody Cursos curso) {
        return cursoServicio.guardarCurso(curso);
    }
    @DeleteMapping("/cursos/eliminar/{id}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Integer id) {
        try {
            cursoServicio.eliminarCurso(id);
            return ResponseEntity.ok("Curso eliminado exitosamente");
        } catch (CustomException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    @PutMapping("/Curso/{id}")
    public Cursos ActualizarCurso(@PathVariable Integer id,@RequestBody Cursos cursoActualizado) {
        return cursoServicio.actualizarCurso(id, cursoActualizado);

    }
}
