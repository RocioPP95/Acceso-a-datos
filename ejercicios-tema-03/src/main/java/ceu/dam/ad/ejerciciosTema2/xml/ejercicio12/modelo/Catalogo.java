package ceu.dam.ad.ejerciciosTema2.xml.ejercicio12.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Catalogo {
	
	private Integer tamaño;
	private List<Articulo> articulos;

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}



	public Integer getTamaño() {
		return tamaño;
	}

	public void setTamaño(Integer tamaño) {
		this.tamaño = tamaño;
	}

	@Override
	public String toString() {
		return "Catalogo [tama�o=" + tamaño + ", articulos=" + articulos + "]";
	}

	public static Catalogo createRandomObject(Integer tamaño) {
		Catalogo c = new Catalogo();
		c.setTamaño(tamaño);
		c.setArticulos(new ArrayList<>());
		for (int i = 0; i < tamaño; i++) {
			c.getArticulos().add(createRandomArticulo());
		}
		return c;
	}
	
	private static Articulo createRandomArticulo() {
		Random r = new Random();
		String[] tallas = {"S", "M", "L", "XL", "XXL"};
		String[] colores = {"Rojo", "Verde", "Negro", "Azul", "Amarillo", "Naranja"};
		Articulo a = new Articulo();
		a.setDescripcion(generateRandomWords(3));
		Integer precio = Integer.parseInt(generateRandomNumber(5));
		a.setPvp(new Pvp());
		a.getPvp().setPrecio(new BigDecimal(precio).divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN));
		a.getPvp().setIva(a.getPvp().getPrecio().divide(new BigDecimal(9), 2, RoundingMode.HALF_DOWN));
		a.setSku(generateRandomWords(1)+generateRandomNumber(5));
		a.setCategorias(new ArrayList<>());
		a.setModelos(new ArrayList<>());
		Integer numCategorias = r.nextInt(4)+1;
		Integer numModelos = r.nextInt(5)+2;
		for (int i = 0; i < numCategorias; i++) {
			Categoria c = new Categoria();
			a.getCategorias().add(c);
			c.setNombre(generateRandomWords(2));
		}
		for (int i = 0; i < numModelos; i++) {
			Modelo m = new Modelo();
			a.getModelos().add(m);
			m.setColor(colores[r.nextInt(6)]);
			m.setTalla(tallas[r.nextInt(5)]);
			m.setCodigosBarra(new ArrayList<>());
			Integer numCodigos = r.nextInt(3)+1;
			for (int j = 0; j < numCodigos; j++) {
				m.getCodigosBarra().add(generateRandomNumber(13));
			}
		}
		return a;
	}
	
	private static String generateRandomWords(int numberOfWords){
		Random random = new Random();
	    String randomStrings = "";
	    for(int i = 0; i < numberOfWords; i++)   {
	        char[] word = new char[random.nextInt(8)+3]; 
	        for(int j = 0; j < word.length; j++)  {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomStrings += new String(word);
	        if (i<numberOfWords-1) {
	        	randomStrings += " ";
	        }
	    }
	    return randomStrings;
	}
	
	private static String generateRandomNumber(int cifras){
		Random random = new Random();
	    String randomStrings = "";
	    for(int i = 0; i < cifras; i++)   {
	        Integer cifra = random.nextInt(10);
	        randomStrings+=cifra;
	    }
	    return randomStrings;
	}
	
	

}
