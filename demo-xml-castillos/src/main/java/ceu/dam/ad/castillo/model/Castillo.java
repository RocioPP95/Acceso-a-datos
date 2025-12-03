package ceu.dam.ad.castillo.model;

import java.util.List;

import lombok.Data;

@Data
public class Castillo {

	private Boolean foso;
	private List<Caballero> caballeros;
	private List<Dragon> dragones;

}
