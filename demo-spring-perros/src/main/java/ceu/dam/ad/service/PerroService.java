package ceu.dam.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.Perro;
import ceu.dam.ad.repository.perros.PerrosRepository;
import jakarta.transaction.Transactional;

@Service
public class PerroService {

	// para decirle a spring que un atributo me loinstancie automaticamente
	@Autowired
	private PerrosRepository repo;

	public Perro crearPerro(Perro perro) {
		// repo equivale a dao
		// el save equivale a guardar en la tabla el perro
		repo.save(perro);
		return perro;
	}

	// esto es para que si hay algun error no cree nada
	@Transactional
	public void crearPerros(List<Perro> perros) {
		// perros.forEach(p -> repo.save(p));
		repo.saveAll(perros); // no hace falta poner transactional
	}

	public Perro consultarPerro(Long id) throws NotFoundException {
		//
		Optional<Perro> optionalPerro = repo.findById(id);

		if (optionalPerro.isPresent()) {
			return optionalPerro.get();

		}
		throw new NotFoundException("No existe perro");
	}

	List<Perro> findByPeroneAndRaza(String nombre, String raza) {
		return repo.findByPeroneAndRaza(nombre, raza);
	}

	

}
