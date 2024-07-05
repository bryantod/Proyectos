package com.backend.curso.Entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ListaDeseos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Usuarios.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarios;

    private Date fechaC;

    @ManyToOne
    @JoinColumn(name = "Id_Curso")
    private Cursos cursos;

    public ListaDeseos() {
    }

    public ListaDeseos(int id, Usuarios usuarios, Date fechaC, Cursos cursos) {
        this.id = id;
        this.usuarios = usuarios;
        this.fechaC = fechaC;
        this.cursos = cursos;
    }

    public ListaDeseos(Usuarios usuarios, Cursos cursos) {
        this.usuarios = usuarios;
        this.cursos = cursos;
        this.fechaC = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
        this.fechaC = fechaC;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }
}
