package ceu.dam.ad.ejerciciosTema2.xml.ejercicio12.modelo;

import java.math.BigDecimal;

public class Pvp {

	private BigDecimal precio;
	private BigDecimal iva;
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	@Override
	public String toString() {
		return "Pvp [precio=" + precio + ", iva=" + iva + "]";
	}
	
	

}
