package ceu.dam.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.Perro;
import ceu.dam.ad.repository.perros.PerrosRepository;

@Service
public class PerroService {
	@Autowired
	private PerrosRepository repo;

	public Perro crearPerro(Perro perro) {
		// repo equivale a dao
		// el save equivale a guardar en la tabla el perro
		repo.save(perro);
		return perro;
	}

	public Perro consultarPerro(Long id) throws NotFoundException {
		Optional<Perro> optionalPerro = repo.findById(id);

		if (optionalPerro.isPresent()) {
			return optionalPerro.get();

		}
		throw new NotFoundException("No existe perro");
	}
//	public List<Perro>buscarPerrosPorNombre(String filtroNombre){
//		repo.findByPeroneContains("");
//	}

}
