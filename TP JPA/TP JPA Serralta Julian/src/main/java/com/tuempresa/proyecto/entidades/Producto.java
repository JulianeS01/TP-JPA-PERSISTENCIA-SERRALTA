package com.tuempresa.proyecto.entidades;

import com.tuempresa.proyecto.ClasesEN.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends EntidadBase{

    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVta;
    private double precioCompra;
    private int stockActual;
    private int stockMin;
    private String unidadMedida;
    private String receta;
    private Tipo tipo;
}
