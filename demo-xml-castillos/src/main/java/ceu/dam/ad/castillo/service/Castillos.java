package ceu.dam.ad.castillo.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ceu.dam.ad.castillo.model.Castillo;
import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonRootName(value = "castillos")
@Data
public class Castillos {
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("castillo")
	private List<Castillo> castillos;
	
	
}
