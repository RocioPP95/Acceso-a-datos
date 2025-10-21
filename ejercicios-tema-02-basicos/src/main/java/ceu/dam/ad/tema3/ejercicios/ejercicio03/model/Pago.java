package ceu.dam.ad.tema3.ejercicios.ejercicio03.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Pago {
	private LocalDate fecha;
	private BigDecimal importe;
	
	
}
