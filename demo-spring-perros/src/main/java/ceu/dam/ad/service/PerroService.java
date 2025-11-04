package ceu.dam.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.Perro;
import ceu.dam.ad.model.Persona;
import ceu.dam.ad.repository.perros.PerrosRepository;
import ceu.dam.ad.repository.perros.PersonaRepository;
import jakarta.transaction.Transactional;

@Service
public class PerroService {

	// para decirle a spring que un atributo me loinstancie automaticamente
	@Autowired
	private PerrosRepository repoPerro;
	@Autowired
	private PersonaRepository repoPersona;

	public Perro crearPerro(Perro perro) {
		// repo equivale a dao
		// el save equivale a guardar en la tabla el perro
		repoPerro.save(perro);
		return perro;
	}

	@Transactional
	public void crearPersona(Persona p) {
		repoPersona.save(p);

	}

	public Persona consultarPersona(Long id) {
		// si el optional esta vacío -> lanza la ecepción
		return repoPersona.findById(id).orElseThrow(() -> new RuntimeException("No existe"));
	}

}
