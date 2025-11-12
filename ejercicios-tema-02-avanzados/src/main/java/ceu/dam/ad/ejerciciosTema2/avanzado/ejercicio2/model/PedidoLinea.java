package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pedido_lineas")
public class PedidoLinea {
	@Id
	@GeneratedValue
	@Column(name = "uiid_linea_pedido")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID uidLinea;
	private Integer numLinea;
	private Integer cantidad;
	@Column(name = "id_articulo")

	private Articulo articulo;

}
