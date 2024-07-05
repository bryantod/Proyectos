package com.backend.curso.dto;

public class RespuestaDto {

    private String estado;
    private String mensaje;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public RespuestaDto(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }
}