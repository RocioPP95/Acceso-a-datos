package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Marca;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Pais;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Tienda;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.ReposirotyMarca;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository.RepositoryPais;

@Service
public class ComercialServiceImpl implements ComercialService {
	@Autowired
	private RepositoryPais repoPais;
	@Autowired
	private ReposirotyMarca repoMarca;

	@Override
	public List<Pais> buscarPaises(String filtro) throws ComercialException {
		List<Pais> paises = new ArrayList<>();

		try {
			paises = repoPais.findByDescripcionStartingWith(filtro);
		} catch (DataAccessException e) {
			throw new ComercialException("Error al nusvar los países", e);
		}

		return paises;
	}

	@Override
	public void insertarMarca(Marca marca) throws ComercialException {
		try {
			repoMarca.save(marca);
		} catch (DataAccessException e) {
			throw new ComercialException("No se ha podido insertar la marca", e);
		}
	}

	@Override
	public void insertarCentroComercial(CentroComercial cc) throws ComercialException {
	}

	@Override
	public CentroComercial consultarCentroComercial(String uuidCentro) throws ComercialException, NotFoundException {
		return null;
	}

	@Override
	public Tienda consularTienda(Long idTienda) throws ComercialException, NotFoundException {
		return null;
	}

	@Override
	public void borrarTienda(Long idTienda) throws ComercialException {
	}

	@Override
	public void borrarCentroComercial(String uuidCentro) throws ComercialException {
	}

}
