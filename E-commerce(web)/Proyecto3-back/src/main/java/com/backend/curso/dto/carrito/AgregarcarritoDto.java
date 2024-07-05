package com.backend.curso.dto.carrito;

import jakarta.validation.constraints.NotNull;

public class AgregarcarritoDto {

    private Integer id;
    private @NotNull Integer cursoId;

    public AgregarcarritoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(@NotNull Integer cursoId) {
        this.cursoId = cursoId;
    }
}
