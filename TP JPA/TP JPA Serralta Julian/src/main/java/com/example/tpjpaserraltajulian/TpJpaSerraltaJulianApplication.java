package com.example.tpjpaserraltajulian;

import com.tuempresa.proyecto.ClasesEN.Estado;
import com.tuempresa.proyecto.ClasesEN.FormaPago;
import com.tuempresa.proyecto.ClasesEN.Tipo;
import com.tuempresa.proyecto.ClasesEN.TipoEnvio;
import com.tuempresa.proyecto.entidades.*;
import com.tuempresa.proyecto.repositorios.ClienteRepository;
import com.tuempresa.proyecto.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class TpJpaSerraltaJulianApplication {

	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpJpaSerraltaJulianApplication.class, args);
		System.out.println("Ready");
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			System.out.println("----------------READY---------------------");
			//crear instancia rubro
			Rubro rubro1 = Rubro.builder()
					.denominacion("Hamburguesas")
					.build();
			//crear instancias de productos
			Producto producto1 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa Triple")
					.precioVta(2999)
					.precioCompra(1000)
					.stockActual(20)
					.stockMin(1)
					.unidadMedida("unidad1")
					.receta("receta1")
					.tipo(Tipo.Insumo)
					.build();
			Producto producto2 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa Doble")
					.precioVta(2499)
					.precioCompra(1000)
					.stockActual(42)
					.stockMin(1)
					.unidadMedida("unidad2")
					.receta("receta2")
					.tipo(Tipo.Insumo)
					.build();
			//agregar productos al rubro
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			//guardar rubro
			rubroRepository.save(rubro1);
			//Crear instancia Cliente
			Cliente cliente1 = Cliente.builder()
					.nombre("Julian")
					.apellido("Serralta")
					.mail("julianserralta@gmail")
					.telefono("telefono1")
					.build();
			//Crear instancia domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Pinot Noir")
					.numero(2323)
					.localidad("Maipu")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Godoy Cruz")
					.numero(1230)
					.localidad("Godoy Cruz")
					.build();
			//agregar domicilios a cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			//Crear Instancia Detalle Pedido
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(9000)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2499)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(9000)
					.build();
			//configuracion fecha
			SimpleDateFormat formatoFecha = new SimpleDateFormat ("yyyy-MM-dd");
			String fechaString = "2023-09-15";
			// Parsear la cadena en un objeto Date
			Date fecha = formatoFecha.parse(fechaString);
			//Crear Instancia Pedido
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha)
					.total(9000)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.total(5000)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.build();
			//Crear instancias de factura
			Factura factura1 = Factura.builder()
					.fecha(fecha)
					.total(8600)
					.numero(1)
					.dto(400)
					.formaPago(FormaPago.Efectivo)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha)
					.total(4400)
					.numero(2)
					.dto(600)
					.formaPago(FormaPago.Efectivo)
					.build();
			//Agregar detalles al pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
			//agregar pedidos al cliente
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);
			//vincular el detalle pedido con el producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			//vincular factura con pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			//guardar cliente
			clienteRepository.save(cliente1);

			//recuperar objeto rubro desde la base de datos
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}
			//recuperar Cliente desde la base de Datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getMail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}
		};
	}

}
