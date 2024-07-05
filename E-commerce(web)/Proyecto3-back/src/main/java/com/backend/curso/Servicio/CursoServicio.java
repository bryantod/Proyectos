package com.backend.curso.Servicio;

import com.backend.curso.Entidades.Cursos;
import com.backend.curso.Excepciones.CustomException;
import com.backend.curso.Repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServicio {
    private final CursoRepositorio cursoRepositorio;

    @Autowired
    public CursoServicio(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    public Cursos findById(Integer cursoId) throws CustomException {
        Optional<Cursos> optionalCursos = cursoRepositorio.findById(cursoId);
        if (optionalCursos.isEmpty()) {
            throw new CustomException("Código de curso no válido: " + cursoId);
        }
        return optionalCursos.get();
    }

    public List<Cursos> getAllCursos() {
        return cursoRepositorio.findAll();
    }

    public Cursos guardarCurso(Cursos curso) {
        return cursoRepositorio.save(curso);
    }

    public void eliminarCurso(Integer id) throws CustomException {
        if (cursoRepositorio.existsById(id)) {
            cursoRepositorio.deleteById(id);
        } else {
            throw new CustomException("Código de curso no válido: " + id);
        }
    }

    public Cursos actualizarCurso(Integer id, Cursos cursoActualizado) {
        if (cursoRepositorio.existsById(id)) {
            cursoActualizado.setIdCurso(id);
            return cursoRepositorio.save(cursoActualizado);
        } else {
            try {
                throw new CustomException("Código de curso no válido: " + id);
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Cursos getCursos(Cursos cursos) {
        Cursos cursoss = new Cursos();
        cursoss.setDescripcion(cursos.getDescripcion());
        cursoss.setDuracion(cursos.getDuracion());
        cursoss.setIdCurso(cursos.getIdCurso());
        cursoss.setImagenCurso(cursos.getImagenCurso());
        cursoss.setNivel(cursos.getNivel());
        cursoss.setNombreCurso(cursos.getNombreCurso());
        cursoss.setDescuento(cursos.getDescuento());
        cursoss.setPrecio(cursos.getPrecio());
        return cursoss;
    }
}
