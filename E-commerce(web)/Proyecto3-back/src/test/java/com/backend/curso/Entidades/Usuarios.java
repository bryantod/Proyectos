package com.backend.curso.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;
@Data
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "metodopago")
    private MetodoPago metodoPago;

    @Column(name = "fotodeperfil")
    private String fotoDePerfil;

    // Métodos
    public void validarUsuario() {
        // Lógica de validación de usuario
    }

    public void actualizarPerfil() {
        // Lógica de actualización de perfil
    }
}
