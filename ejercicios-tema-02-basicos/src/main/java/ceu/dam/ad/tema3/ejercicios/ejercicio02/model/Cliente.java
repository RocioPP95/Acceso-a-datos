package ceu.dam.ad.tema3.ejercicios.ejercicio02.model;

import lombok.Data;

@Data
public class Cliente {

	private Integer id;
	private String nombre;
	private String apellidos;
	private String email;
	private Boolean activo;
	
	
}
