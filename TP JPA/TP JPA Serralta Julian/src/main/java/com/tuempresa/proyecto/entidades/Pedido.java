package com.tuempresa.proyecto.entidades;

import com.tuempresa.proyecto.ClasesEN.Estado;
import com.tuempresa.proyecto.ClasesEN.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends EntidadBase{

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double total;
    private TipoEnvio tipoEnvio;
    private Estado estado;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "factura-id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER) //Es necesario agregar el parametro de "FetchType.EAGER"
    @JoinColumn(name = "Pedido-id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detallePedidoed){

        detallePedidos.add(detallePedidoed);
    }
}
