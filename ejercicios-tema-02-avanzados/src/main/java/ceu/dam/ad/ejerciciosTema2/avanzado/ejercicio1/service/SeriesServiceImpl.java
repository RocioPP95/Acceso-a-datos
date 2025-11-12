package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Serie;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository.RepositoryEpisodio;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository.RepositorySerie;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository.RepositoryTemporada;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository.RepositoryTrailer;
import jakarta.transaction.Transactional;

@Service
public class SeriesServiceImpl implements SeriesService {
	@Autowired
	private RepositoryEpisodio repoEpisodio;
	@Autowired
	private RepositorySerie repoSerie;
	@Autowired
	private RepositoryTemporada repoTemporada;
	@Autowired
	private RepositoryTrailer repoTrailer;

	@Override
	public Serie consultarSerie(Long idSerie) throws SerieNotFoundException, SeriesServiceException {
		try {
			return repoSerie.findById(idSerie).orElseThrow(() -> new SerieNotFoundException("No existe"));
		} catch (DataAccessException e) {
			throw new SeriesServiceException(e);
		}

	}

	@Override

	public List<Serie> buscarSeries(String filtroDescripcion) throws SerieNotFoundException, SeriesServiceException {
		try {
			List<Serie> series = repoSerie.findByDescripcionContaining(filtroDescripcion);

			if (series == null || series.isEmpty()) {
				throw new SerieNotFoundException("No se ha encontrado nada ");
			}

			return series;

		} catch (DataAccessException e) {
			throw new SeriesServiceException(e);
		}

	}

	@Override
	@Transactional
	public Serie crearSerie(Serie serie) throws SeriesServiceException {
		try {
			repoSerie.save(serie);
		} catch (DataAccessException e) {
			throw new SeriesServiceException(e);
		}
		return serie;

	}

	@Override
	@Transactional
	public void elimnarSerie(Long idSerie) throws SeriesServiceException {
		try {
			repoSerie.deleteById(idSerie);
		} catch (DataAccessException e) {
			throw new SeriesServiceException(e);
		}

	}

	@Override
	@Transactional
	public void actualizarSerie(Serie serie) throws SeriesServiceException {
		try {
			repoSerie.save(serie);
		} catch (DataAccessException e) {
			throw new SeriesServiceException(e);
		}

	}

}
