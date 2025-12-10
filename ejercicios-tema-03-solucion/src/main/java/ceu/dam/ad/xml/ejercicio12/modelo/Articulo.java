package ceu.dam.ad.xml.ejercicio12.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


public class Articulo {

	@JsonProperty(value="identificador")
	private String sku;
	
	private String descripcion;
	
	@JsonProperty("precio")
	private Pvp pvp;
	
	@JacksonXmlElementWrapper(localName= "modelosDisponibles")
	@JsonProperty("modelo")
	private List<Modelo> modelos;
	
	@JacksonXmlElementWrapper(localName= "categorias")
	@JsonProperty("categoria")
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
