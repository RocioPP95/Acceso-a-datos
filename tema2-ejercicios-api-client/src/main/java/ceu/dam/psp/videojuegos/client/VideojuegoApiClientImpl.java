package ceu.dam.psp.videojuegos.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;

public class VideojuegoApiClientImpl implements VideojuegoApiClient {
	private static final Logger log = LoggerFactory.getLogger(VideojuegoApiClientImpl.class);

	private String urlBase;
	// Este atributo contendrá la
	// URL base a la que hacer
	// las peticiones
	private HttpClient client;

	public VideojuegoApiClientImpl(String uuidUrl) {
		// El constructor recibe el identificador que ha generado crudcrud.com para
		// nuestro API y construye la URL base
		urlBase = "https://crudcrud.com/api/" + uuidUrl + "/";
		client = HttpClient.newHttpClient();

	}

	/**
	 * Tiene que devolver el videojuego con el ID indicado. Si no existe, lanzará
	 * NotFoundException. Si hay cualquier otro error, lanzará ApiException
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	@Override
	public Videojuego findById(String id) throws NotFoundException, ApiException {
		try {
			URI url = new URI(urlBase + "/" + id);
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();

			HttpResponse<String> respuesta = client.send(request, BodyHandlers.ofString());
			System.out.println(respuesta.statusCode());
			System.out.println(respuesta.body());
			List<Videojuego> videojuegos = Arrays.asList(new Gson().fromJson(respuesta.body(), Videojuego[].class));
			if (videojuegos.isEmpty()) {

				throw new NotFoundException("No se encontró videojuego con este id");
			}
			return videojuegos.get(0);
		} catch (Exception e) {
			throw new ApiException("Error en la api ", e);
		}
	}

	/**
	 * Tiene que devolver la lista de videojuegos que tengan el año de publicación
	 * indicado. Si no hay ninguno, lanzará NotFoundException. Si hay cualquier otro
	 * error, lanzará ApiException
	 * 
	 * @param año
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	@Override
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException {
		try {
			URI url = new URI(urlBase);
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();

			HttpResponse<String> respuesta = client.send(request, BodyHandlers.ofString());
			System.out.println(respuesta.statusCode());
			System.out.println(respuesta.body());
			List<Videojuego> videojuegos = Arrays.asList(new Gson().fromJson(respuesta.body(), Videojuego[].class));
			List<Videojuego> videojuegosFiltrados = new ArrayList<>();
			for (Videojuego juego : videojuegos) {
				if (año.equals(juego.getAñoPublicacion())) {
					videojuegosFiltrados.add(juego);
				}
			}

			if (videojuegos.isEmpty()) {
				throw new NotFoundException("No se encontró videojuego con este año de publicación");
			}
			if (videojuegosFiltrados.isEmpty()) {
				throw new NotFoundException("No se encontró videojuego con este año de publicación");
			}
			return videojuegosFiltrados;
		} catch (Exception e) {
			throw new ApiException("Error en la api ", e);
		}

	}

	/**
	 * Debe crear el videojuego recibido. Devolverá el ID generado. Si hay cualquier
	 * error, lanzará ApiException
	 * 
	 * @param videojuego
	 * @return
	 * @throws ApiException
	 */
	@Override
	public String create(Videojuego videojuego) throws ApiException {
		Videojuego creado = null;
		try {
			String json = new Gson().toJson(videojuego);
			// System.out.println("Request bosy: " + json);

			URI url = new URI("https://crudcrud.com/api/0edc26ae00ac43088906ca7e436db4c0/videojuegos");

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).header("Content-Type", "application/json")
					.POST(BodyPublishers.ofString(json)).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.body());
			creado = new Gson().fromJson(response.body(), Videojuego.class);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return creado.getId();
	}

	/**
	 * Debe actualizar el videojuego indicado. Si no existe, lanzará
	 * NotFoundException. Si hay cualquier otro error, lanzará ApiException
	 * 
	 * @param videojuego
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	@Override
	public void update(Videojuego videojuego) throws NotFoundException, ApiException {

		try {

			Videojuego copia = new Videojuego();
			copia.setNombre(videojuego.getNombre());
			copia.setValoracion(videojuego.getValoracion());
			copia.setAñoPublicacion(videojuego.getAñoPublicacion());
			copia.setPaisOrigen(videojuego.getPaisOrigen());

			Gson gson = new Gson();
			String json = gson.toJson(copia);

			URI url = URI.create(urlBase + "/" + videojuego.getId());
			HttpRequest request = HttpRequest.newBuilder(url).header("Content-Type", "application/json")
					.PUT(BodyPublishers.ofString(json)).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if (response.statusCode() == 404) {
				throw new NotFoundException("No existe videojuego con  ese id ");
			}

		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Error al actualizar videojuego", e);
		}
	}

	/**
	 * Debe borrar el videojuego con ID indicado. Si no existe, lanzará
	 * NotFoundException. Si hay cualquier otro error, lanzará ApiException
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	@Override
	public void delete(String id) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase + "/" + id);
			HttpRequest request = HttpRequest.newBuilder(url).DELETE().build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			// Si la API devuelve 404, significa que no existe el recurso
			if (response.statusCode() == 404) {
				throw new NotFoundException("No existe videojuego conese id");
			}

		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Error al borrar videojuego", e);
		}
	}

}
