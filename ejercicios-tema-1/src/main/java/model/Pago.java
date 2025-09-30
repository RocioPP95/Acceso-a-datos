package model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Pago {
	private Integer importe;
	private LocalDate fecha;

}
