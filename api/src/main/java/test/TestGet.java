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
			URI url = new URI("https://crudcrud.com/api/07abfc3271f74cc194793be6baafdfc8/videojuegos");
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.body());

			List<Pelicula> peliculas = Arrays.asList(new Gson().fromJson(response.body(), Pelicula[].class));

			peliculas.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
