package ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@Column(name="id_pedido")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;
	
	private LocalDate fechaPedido;
	private LocalDate fechaEntrega;
	private String cliente;
	@Transient
	private List<PedidoLinea> lineas;
	
	

}
