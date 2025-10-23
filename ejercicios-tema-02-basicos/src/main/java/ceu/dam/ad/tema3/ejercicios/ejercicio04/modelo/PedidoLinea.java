package ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pedidos_lineas")
public class PedidoLinea {
	@Id
	private Long idPedido;
	@Column(name = "numero_linea")
	private Integer numLinea;
	private String articulo;
	private BigDecimal precio;

}
