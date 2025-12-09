package ceu.dam.ad.castillo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Data
@JsonRootName(value = "castillo")
public class Castillo {

	@JsonProperty("tieneFoso")
	private Boolean foso;
	
	@JacksonXmlElementWrapper(localName =  "caballeros")
	@JsonProperty("caballero")
	private List<Caballero> caballeros;
	
	@JacksonXmlElementWrapper(localName =  "dragones")
	@JsonProperty("dragon")
	private List<Dragon> dragones;

}
