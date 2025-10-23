package ceu.dam.ad.tema3.ejercicios.ejercicio03.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Pago {

	@Column(name = "payment_date")
	private LocalDate fecha;
	@Id
	@Column(name = "payment_id")
	private Integer id;

	@Column(name = "amount")
	private BigDecimal importe;

	@Column(name = "customer_id")
	private Integer idCliente;

}
