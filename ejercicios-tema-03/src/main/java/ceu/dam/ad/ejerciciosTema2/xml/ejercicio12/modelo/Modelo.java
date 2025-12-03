package ceu.dam.ad.ejerciciosTema2.xml.ejercicio12.modelo;

import java.util.List;

public class Modelo {
	private String talla;
	private String color;
	private List<String> codigosBarra;
	
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<String> getCodigosBarra() {
		return codigosBarra;
	}
	public void setCodigosBarra(List<String> codigosBarra) {
		this.codigosBarra = codigosBarra;
	}
	@Override
	public String toString() {
		return "Modelo [talla=" + talla + ", color=" + color + ", codigosBarra=" + codigosBarra + "]";
	}
	


}
