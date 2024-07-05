package com.backend.curso.Servicio;

import com.backend.curso.Entidades.Cursos;
import com.backend.curso.Entidades.ListaDeseos;
import com.backend.curso.Entidades.Usuarios;
import com.backend.curso.Repositorio.ListaDeseosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaDeseosServicio {
    @Autowired
    ListaDeseosRepositorio listaDeseosRepositorio;
    @Autowired
    CursoServicio cursoServicio;

    public void crearListaDeseos(ListaDeseos listaDeseos) {
    listaDeseosRepositorio.save(listaDeseos);
    }

    public List<Cursos> obtenerListaDeseosPorUsuario(Usuarios usuarios){
        final List<ListaDeseos> listaDeseoss = listaDeseosRepositorio.findAllByusuariosOrderByCursos(usuarios);
        List<Cursos> cursos = new ArrayList<>();
        for(ListaDeseos listaDeseos: listaDeseoss){
            cursos.add(cursoServicio.getCursos(listaDeseos.getCursos()));
        }
        return cursos;
    }

    public void eliminarDeListaDeseos(Usuarios usuario, int idCurso) {
        List<ListaDeseos> listaDeseos = listaDeseosRepositorio.findAllByusuariosOrderByCursos(usuario);
        for (ListaDeseos listaDeseos1 : listaDeseos) {
            if (listaDeseos1.getCursos().getIdCurso() == idCurso) {
                listaDeseosRepositorio.delete(listaDeseos1);
                return;
            }
        }
    }
}
