package com.backend.curso.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "Pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_Pedido;

    @ManyToOne
    @JoinColumn(name = "Id_Usuario")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "Id_Curso")
    private Cursos curso;

    private Date FechaPedido;

    // Métodos
    public void actualizarEstadoPedido() {
    }

    public void calcularTotal() {
    }
}