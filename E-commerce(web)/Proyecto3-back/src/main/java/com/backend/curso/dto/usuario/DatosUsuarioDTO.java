package com.backend.curso.dto.usuario;

import java.sql.Blob;

public class DatosUsuarioDTO {
    private int perfil_id;
    private String nombre;
    private String apellido;
    private String correo;
    private String Img;

    public DatosUsuarioDTO(int perfil_id, String nombre, String apellido, String correo, String img) {
        this.perfil_id = perfil_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        Img = img;
    }

    public int getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
