package com.backend.curso.Entidades;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "tokens")
public class TokenAutenticacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "fecha_creada")
    private Date fechacreada;

    @OneToOne(targetEntity = Usuarios.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuarios usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechacreada() {
        return fechacreada;
    }

    public void setFechacreada(Date fechacreada) {
        this.fechacreada = fechacreada;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public TokenAutenticacion() {
    }

    public TokenAutenticacion(Usuarios usuario) {
        this.usuario = usuario;
        this.fechacreada = new Date();
        this.token = UUID.randomUUID().toString();
    }
}
