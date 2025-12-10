package ceu.dam.ad.xml.ejercicio09;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.ejercicio06.modelo.Persona;

public class PeliculasXMLHandler extends DefaultHandler {

	private List<Largometraje> peliculas;
	private Boolean openTag;
	private String openData;
	private Largometraje pelicula;
	private List<Persona> artistasInterpretacion;
	private Persona artista;

	public PeliculasXMLHandler() {
		peliculas = new ArrayList<>();
	}

	public List<Largometraje> getPeliculas() {
		return peliculas;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		openTag = true;
		openData = "";

		if (qName.equals("pelicula")) {
			pelicula = new Largometraje();
			peliculas.add(pelicula);
		}
		else if (qName.equals("artistas")) {
			artistasInterpretacion = new ArrayList<>();
			pelicula.setActores(artistasInterpretacion);
		}
		else if (qName.equals("artista")) {
			artista = new Persona();
			String tipo = attributes.getValue("tipo");
			if (tipo.equals(Persona.DIRECCION)) {
				pelicula.setDireccion(artista);
			}
			else if (tipo.equals(Persona.PRODUCCION)) {
				pelicula.setProduccion(artista);
			}
			else {
				artistasInterpretacion.add(artista);
			}
				
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		openTag = false;

		if (qName.equals("titulo")) {
			pelicula.setTitulo(openData);
		}
		else if (qName.equals("duracion")) {
			pelicula.setDuracion(Integer.parseInt(openData));
		}
		else if (qName.equals("a�o")) {
			pelicula.setAño(Integer.parseInt(openData));
		}
		else if (qName.equals("nombre")) {
			artista.setNombre(openData);
		}
		else if (qName.equals("nacionalidad")) {
			artista.setNacionalidad(openData);
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (openTag != null) {
			openData += new String(ch, start, length);
		}
	}
}
