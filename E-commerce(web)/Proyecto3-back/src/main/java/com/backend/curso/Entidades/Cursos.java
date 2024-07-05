package com.backend.curso.Entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    @JsonProperty("id_curso")
    private int idCurso;

    @Column(name = "descripcion")
    @JsonProperty("descripcion")
    private String descripcion;

    @Column(name = "duracion")
    @JsonProperty("duracion")
    private int duracion;

    @Column(name = "imagen_curso")
    @JsonProperty("imagen_curso")
    private String imagenCurso;

    @Column(name = "nivel")
    @JsonProperty("nivel")
    private int nivel;

    @Column(name = "nombre_curso")
    @JsonProperty("nombre_curso")
    private String nombreCurso;

    @Column(name = "descuento")
    @JsonProperty("descuento")
    private int descuento;

    @Column(name = "precio")
    @JsonProperty("precio")
    private float precio;

    public Cursos(int idCurso, String descripcion, String imagenCurso, int duracion, int nivel, String nombreCurso, int descuento, float precio) {
        this.idCurso = idCurso;
        this.descripcion = descripcion;
        this.imagenCurso = imagenCurso;
        this.duracion = duracion;
        this.nivel = nivel;
        this.nombreCurso = nombreCurso;
        this.descuento = descuento;
        this.precio = precio;
    }

    public Cursos() {
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getImagenCurso() {
        return imagenCurso;
    }

    public void setImagenCurso(String imagenCurso) {
        this.imagenCurso = imagenCurso;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
