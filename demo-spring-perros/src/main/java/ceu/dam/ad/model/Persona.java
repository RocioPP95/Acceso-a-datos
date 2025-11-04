package ceu.dam.ad.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;
	private String dni;
	private String nombre;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ferch es para select get y cascade es para delete
																	// cambio actualizo....
	@JoinColumn(name = "id_persona", nullable = false)
	private List<Perro> perros;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_domicilio", nullable = false)
	private Domicilio domicilio;

}