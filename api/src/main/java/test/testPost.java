package test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class testPost {
	public static void main(String[] args) {

		try {
			Pelicula nueva = new Pelicula();
			nueva.setAñoPublicacion(90);
			nueva.setNombre("lalalal");
			nueva.setPaisOrigen("jajaja");

			String json = new Gson().toJson(nueva);
			System.out.println("Request bosy: " + json);

			URI url = new URI("https://crudcrud.com/api/07abfc3271f74cc194793be6baafdfc8/videojuegos");
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).header("Content-Type", "application/json")
					.POST(BodyPublishers.ofString(json)).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.body());
			Pelicula creada = new Gson().fromJson(response.body(), Pelicula.class);

			System.out.println(creada);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}