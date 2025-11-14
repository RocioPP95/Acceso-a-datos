package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido_lineas")
public class PedidoLinea {
	@Id
	@GeneratedValue
	@Column(name = "uuid_linea_pedido")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID uidLinea;
	private Integer numLinea;
	private Integer cantidad;
	@ManyToOne                      
    @JoinColumn(name = "id_articulo")
	private Articulo articulo;

}
