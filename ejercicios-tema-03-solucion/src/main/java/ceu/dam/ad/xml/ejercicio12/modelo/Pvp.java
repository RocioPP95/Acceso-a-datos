package ceu.dam.ad.xml.ejercicio12.modelo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Pvp {

	@JacksonXmlText
	private BigDecimal precio;
	@JsonIgnore
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
