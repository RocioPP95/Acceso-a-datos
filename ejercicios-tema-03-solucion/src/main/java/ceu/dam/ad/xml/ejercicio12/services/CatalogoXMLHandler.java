package ceu.dam.ad.xml.ejercicio12.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ceu.dam.ad.xml.ejercicio12.modelo.Articulo;
import ceu.dam.ad.xml.ejercicio12.modelo.Catalogo;
import ceu.dam.ad.xml.ejercicio12.modelo.Categoria;
import ceu.dam.ad.xml.ejercicio12.modelo.Modelo;
import ceu.dam.ad.xml.ejercicio12.modelo.Pvp;

public class CatalogoXMLHandler extends DefaultHandler {

	private Boolean openTag;
	private String openData;
	
	private Catalogo catalogo;
	private Articulo articulo;
	private Modelo modelo;

	

	public Catalogo getCatalogo() {
		return catalogo;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		openTag = true;
		openData = "";

		if (qName.equals("catálogo")) {
			catalogo = new Catalogo();
			catalogo.setArticulos(new ArrayList<Articulo>());// inicializamos las listas si no están ya en el constructor del Catalogo
			catalogo.setTamaño(Integer.parseInt(attributes.getValue("tamaño"))); // leemos atributo
		}
		else if (qName.equals("articulo")) {
			articulo = new Articulo();
			articulo.setCategorias(new ArrayList<Categoria>()); // inicializamos las listas si no están ya en el constructor del Articulo
			articulo.setModelos(new ArrayList<Modelo>());
			catalogo.getArticulos().add(articulo);
			
		}
		else if (qName.equals("modelo")) {
			modelo = new Modelo();
			modelo.setCodigosBarra(new ArrayList<String>()); // inicializamos las listas si no están ya en el constructor del Modelo
			articulo.getModelos().add(modelo);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		openTag = false;

		if (qName.equals("descripcion")) {
			articulo.setDescripcion(openData);
		}
		else if (qName.equals("identificador")) {
			articulo.setSku(openData);
		}
		else if (qName.equals("precio")) {
			articulo.setPvp(new Pvp());
			articulo.getPvp().setPrecio(new BigDecimal(openData));
		}
		else if (qName.equals("talla")) {
			modelo.setTalla(openData);
		}
		else if (qName.equals("color")) {
			modelo.setColor(openData);
		}
		else if (qName.equals("codigo")) {
			modelo.getCodigosBarra().add(openData);
		}
		else if (qName.equals("categoria")) {
			Categoria categoria = new Categoria();
			categoria.setNombre(openData);
			articulo.getCategorias().add(categoria);
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
