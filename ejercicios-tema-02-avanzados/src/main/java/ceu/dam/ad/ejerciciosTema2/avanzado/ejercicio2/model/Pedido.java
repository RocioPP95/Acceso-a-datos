package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.Date;
import java.util.List;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Pedido {
	@Id
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	 @Column(name = "uuid_pedido")
	private UUID uidPedido;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dni_cliente")
	 @ToString.Exclude
	private Cliente cliente;
	private Date fecha;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "uuid_pedido", nullable = false)
	private List<PedidoLinea> lineas;
	

	
	
}
