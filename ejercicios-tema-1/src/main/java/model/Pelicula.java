package model;

import lombok.Data;

@Data
public class Pelicula {
	private Integer id;
	private String titulo;
	private Integer longitud;

	public Pelicula() {
	}

}
