package com.backend.curso.Entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fechacreada")
    private Date fechacreada;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Cursos cursos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarios;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        DISPONIBLE,
        COMPRADO
    }

    public Carrito() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechacreada() {
        return fechacreada;
    }

    public void setFechacreada(Date fechacreada) {
        this.fechacreada = fechacreada;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
