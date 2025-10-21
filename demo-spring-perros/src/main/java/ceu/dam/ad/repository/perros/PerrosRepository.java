package ceu.dam.ad.repository.perros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.Perro;

@Repository

// siempre extiende de JpaRepository
// perro es la entiti
public interface PerrosRepository extends JpaRepository<Perro, Long> {
	List<Perro> findByPeroneAndRaza(String nombre, String raza);
	// Optional<Perro>findByNumChip(String numChip);

}
