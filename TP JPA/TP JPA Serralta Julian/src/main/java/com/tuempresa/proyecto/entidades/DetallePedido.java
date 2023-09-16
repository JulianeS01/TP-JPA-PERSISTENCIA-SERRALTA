package com.tuempresa.proyecto.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido extends EntidadBase{

    private int cantidad;
    private double subtotal;

    @ManyToOne()
    @JoinColumn(name = "producto-id")
    private Producto producto;
}
