package ceu.dam.ad.repository.perros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.Persona;

@Repository

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}