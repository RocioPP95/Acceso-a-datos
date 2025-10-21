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

	public VideojuegoApiClientImpl(String uuidUrl) {
		// El constructor recibe el identificador que ha generado crudcrud.com para
		// nuestro API y construye la URL base
		urlBase = "https://crudcrud.com/api/" + uuidUrl + "/videojuegos/";

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
			URI url = new URI(urlBase + id);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();

			HttpResponse<String> respuesta = client.send(request, BodyHandlers.ofString());

			if (respuesta.statusCode() == 404) {

				throw new NotFoundException("No se encontró videojuego con este id");
			}
			Gson gson = new Gson();
			Videojuego videojuego = gson.fromJson(respuesta.body(), Videojuego.class);
			return videojuego;

		} catch (NotFoundException e) {
			throw e;
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
			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder(url).GET().build();
			HttpResponse<String> respuesta = client.send(request, BodyHandlers.ofString());

			// as list pasar el array que te devuelve en lista
			List<Videojuego> videojuegos = Arrays.asList(new Gson().fromJson(respuesta.body(), Videojuego[].class));
			List<Videojuego> videojuegosFiltrados = new ArrayList<>();

			if (videojuegos.isEmpty()) {
				throw new NotFoundException("No se encontró videojuego con este año de publicación");
			}

			// videojuegos.stream().filter(v->v.getAñoPublicacion().equals(año));

			for (Videojuego juego : videojuegos) {
				if (año.equals(juego.getAñoPublicacion())) {
					videojuegosFiltrados.add(juego);
				}
			}
			if (videojuegos.isEmpty()) {
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
		try {
			URI url = URI.create(urlBase);
			HttpClient client = HttpClient.newHttpClient();

			Gson gson = new Gson();
			String json = gson.toJson(videojuego);

			HttpRequest request = HttpRequest.newBuilder(url).header("Content-Type", "application/json")
					.POST(BodyPublishers.ofString(json)).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			Videojuego videojuegoCreado = gson.fromJson(response.body(), Videojuego.class);
			return videojuegoCreado.get_id();

		} catch (Exception e) {
			throw new ApiException("Error al conectar con la API", e);
		}
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
			URI url = URI.create(urlBase + videojuego.get_id());
			HttpClient client = HttpClient.newHttpClient();

			videojuego.set_id(null);
			Gson gson = new Gson();
			String json = gson.toJson(videojuego);

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
			URI url = URI.create(urlBase + id);
			HttpRequest request = HttpRequest.newBuilder(url).DELETE().build();
			HttpClient client = HttpClient.newHttpClient();

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
