package ceu.dam.ad.xml.ejercicio08;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ceu.dam.ad.xml.ejercicio04.modelo.Edicion;
import ceu.dam.ad.xml.ejercicio04.modelo.Libro;

public class LibrosXMLHandler extends DefaultHandler{

	private Boolean openTag;
	private String openText;
	private List<Libro> libros;
	private Libro libro;
	private List<String> autores;
	private List<Edicion> ediciones;
	private Edicion edicion;
	
	public List<Libro> getLibros() {
		return libros;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// Inicializamos el objeto principal (lista o lo que sea)
		libros = new ArrayList<>();
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		openTag = true;
		openText = "";
		
		if (qName.equals("libro")) {
			libro = new Libro();
			libros.add(libro);
			libro.setIsbn(Integer.parseInt(attributes.getValue("isbn")));
		}
		else if(qName.equals("autores")) {
			autores = new ArrayList<>();
			libro.setAutores(autores);
		}
		else if(qName.equals("ediciones")) {
			ediciones = new ArrayList<>();
			libro.setEdiciones(ediciones);
		}
		else if(qName.equals("edicion")) {
			edicion = new Edicion();
			ediciones.add(edicion);
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		openTag = false;
		if (qName.equals("titulo")) {
			libro.setTitulo(openText);
		}
		else if (qName.equals("autor")) {
			autores.add(openText);
		}
		else if (qName.equals("año")) {
			edicion.setAño(Integer.parseInt(openText));
		}
		else if (qName.equals("editorial")) {
			edicion.setEditorial(openText);
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(openTag) {
			openText += new String(ch, start, length);
		}
	}
}
