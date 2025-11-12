package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.Date;
import java.util.List;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Data;

@Entity
@Data

public class Pedido {
	@Id
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID uidPedido;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dni_cliente")
	private Cliente cliente;
	private Date fecha;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "uuid_pedido", nullable = false)
	private List<PedidoLinea> lineas;

}
