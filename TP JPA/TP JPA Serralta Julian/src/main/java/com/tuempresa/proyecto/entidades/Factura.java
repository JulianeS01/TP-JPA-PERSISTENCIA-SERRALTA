package com.tuempresa.proyecto.entidades;

import com.tuempresa.proyecto.ClasesEN.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends EntidadBase{

    private int numero;
    private Date fecha;
    private double dto;
    private int total;
    private FormaPago formaPago;
}

