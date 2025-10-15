package test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class TestGet {

	public static void main(String[] args) {
		try {
			// Se crea un objeto URI con la URL de una API REST que devuelve una lista de
			// "videojuegos"
			URI url = new URI("https://crudcrud.com/api/07abfc3271f74cc194793be6baafdfc8/pelicula");
			// Crea un cliente HTTP que será usado para hacer la solicitud al servidor.
			HttpClient client = HttpClient.newHttpClient();
			// Se construye una solicitud HTTP del tipo GET hacia la URL proporcionada. No
			// se envía aún, solo se prepara.
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			// Imprime el código de estado HTTP (por ejemplo, 200 si fue exitoso).
			System.out.println(response.statusCode());
			// Imprime el contenido de la respuesta, es decir, el cuerpo en formato JSON.
			System.out.println(response.body());

			// Esta línea convierte el JSON en una lista de objetos
			List<Pelicula> peliculas = Arrays.asList(new Gson().fromJson(response.body(), Pelicula[].class));

			// Recorre la lista de objetos Pelicula y los imprime uno a uno.
			peliculas.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
