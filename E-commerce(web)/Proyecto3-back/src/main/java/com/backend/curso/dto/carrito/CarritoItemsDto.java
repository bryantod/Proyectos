package com.backend.curso.dto.carrito;

import com.backend.curso.Entidades.Carrito;
import com.backend.curso.Entidades.Cursos;

public class CarritoItemsDto {

    private int id;
    private Cursos cursos;

    public CarritoItemsDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public CarritoItemsDto(Carrito carrito) {
        this.id = carrito.getId();
        this.setCursos(carrito.getCursos());
    }
}
