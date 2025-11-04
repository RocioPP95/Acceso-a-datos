package ceu.dam.ad.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "perros")

public class Perro {

	@Id
	// este es poque el id se autogenra incrementado si no no se pone
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerro;
	private String nombre;
	private String raza;
	private String numChip;
	private Boolean vacunado;
	
	
	
	
	
	
	
	
	
	
	//private Persona propietario;

}
