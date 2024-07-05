package com.backend.curso.dto.usuario;

public class RespuestaIngresoDto {

    private String mensaje;
    private String token;

    public RespuestaIngresoDto() {
    }

    public RespuestaIngresoDto(String mensaje, String token) {
        this.mensaje = mensaje;
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
