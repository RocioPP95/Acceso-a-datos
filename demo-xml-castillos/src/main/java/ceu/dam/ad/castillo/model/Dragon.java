package ceu.dam.ad.castillo.model;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Data
public class Dragon {

	@JacksonXmlProperty(isAttribute = true, localName = "tieneAlas")
	private Boolean alas;
	@JacksonXmlProperty(isAttribute = true)
	private String color;
	private String raza;
	private Integer poder;

}
