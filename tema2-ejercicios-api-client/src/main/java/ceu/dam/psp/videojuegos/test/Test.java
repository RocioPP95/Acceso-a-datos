package ceu.dam.psp.videojuegos.test;

import ceu.dam.psp.videojuegos.client.VideojuegoApiClientImpl;
import ceu.dam.psp.videojuegos.model.Videojuego;

public class Test {

	public static void main(String[] args) {
		String url = "0edc26ae00ac43088906ca7e436db4c0/videojuegos";
		VideojuegoApiClientImpl client = new VideojuegoApiClientImpl(url);

		String id = "68efb4e9662a3f03e8a68191";

		try {
			Videojuego videojuego = client.findById(id);

			System.out.println(videojuego);

		} catch (Exception e) {
			System.err.println("Error inesperado: " + e.getMessage());
			e.printStackTrace();
		}
	}
//
//		 String uuid = "0edc26ae00ac43088906ca7e436db4c0/videojuegos";
//	        VideojuegoApiClientImpl client = new VideojuegoApiClientImpl(uuid);
//
//	        try {
//	            // Suponiendo que implementaste un método para listar todos sin filtro
//	            List<Videojuego> videojuegos = client.findByAñoPublicacion(null); // o crea método findAll()
//
//	            if (videojuegos.isEmpty()) {
//	                System.out.println("No hay videojuegos en la API");
//	                return;
//	            }
//
//	            System.out.println("Lista de videojuegos y sus IDs:");
//	            for (Videojuego v : videojuegos) {
//	                System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre());
//	            }
//	        } catch (NotFoundException | ApiException e) {
//	            System.err.println("Error consultando videojuegos: " + e.getMessage());
//	        }
//	    }
}
