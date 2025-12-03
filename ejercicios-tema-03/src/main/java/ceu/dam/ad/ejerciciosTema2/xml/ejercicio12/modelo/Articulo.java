package ceu.dam.ad.ejerciciosTema2.xml.ejercicio12.modelo;

import java.util.List;

public class Articulo {

	private String sku;
	private String descripcion;
	private Pvp pvp;
	private List<Modelo> modelos;
	private List<Categoria> categorias;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Pvp getPvp() {
		return pvp;
	}
	public void setPvp(Pvp pvp) {
		this.pvp = pvp;
	}
	public List<Modelo> getModelos() {
		return modelos;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	@Override
	public String toString() {
		return "Articulo [sku=" + sku + ", descripcion=" + descripcion + ", pvp=" + pvp + ", modelos=" + modelos
				+ ", categorias=" + categorias + "]";
	}

	
	
	
}
