package app;

import java.util.List;

import model.Pelicula;
import services.PeliculaException;
import services.PeliculaService;

public class app {
	public static void main(String[] args) {
		PeliculaService service = new PeliculaService();
		try {
			List<Pelicula> peliculas = service.findShortFilms();
			peliculas.forEach(System.out::println);
		} catch (PeliculaException e) {
			e.printStackTrace();
		}
	}

}
