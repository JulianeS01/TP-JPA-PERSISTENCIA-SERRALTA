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
public class Domicilio extends EntidadBase{

    private String calle;
    private int numero;
    private String localidad;
}
