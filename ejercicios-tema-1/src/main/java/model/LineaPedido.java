package model;

import java.math.BigDecimal;

import lombok.Data;

@Data

public class LineaPedido {

	private Integer idPedido;
	private Integer numLiena;
	private String articulo;
	private BigDecimal precio;

}
