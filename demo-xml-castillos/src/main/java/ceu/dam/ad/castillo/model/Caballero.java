package ceu.dam.ad.castillo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlText;

@Data
public class Caballero {

	@JacksonXmlText
	private String nombre;
	@JsonIgnore
	private String caballo;
	@JsonIgnore
	private Integer edad;
	@JsonIgnore
	private String escudero;

}
