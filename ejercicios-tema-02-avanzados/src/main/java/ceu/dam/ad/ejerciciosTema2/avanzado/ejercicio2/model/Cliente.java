package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
	@Id
	private String dni;
	private String nombre;
	private String apellidos;
	 @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "dni_cliente", nullable = false)
	 @ToString.Exclude
	private Set<Pedido> pedidos;
	
	
	
}
