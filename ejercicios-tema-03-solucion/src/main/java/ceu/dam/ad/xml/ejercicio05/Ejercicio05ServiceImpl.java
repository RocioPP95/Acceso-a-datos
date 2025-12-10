package ceu.dam.ad.xml.ejercicio05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ceu.dam.ad.xml.ejercicio04.modelo.Edicion;
import ceu.dam.ad.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public class Ejercicio05ServiceImpl implements Ejercicio05Service {

	private static final Logger log = LoggerFactory.getLogger(Ejercicio05ServiceImpl.class);
	
	@Override
	public List<Libro> importXML(String pathFile) throws XMLImportException {
		try {
			log.info("Importando fichero XML de libros " + pathFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Obtener XML a partir de la ruta
			File file = new File(pathFile);
			Document xml = builder.parse(file);

			// Obtengo el root
			Element librosTag = xml.getDocumentElement();

			List<Libro> libros = new ArrayList<>();

			NodeList libroTagList = librosTag.getElementsByTagName("libro");
			for (int i = 0; i < libroTagList.getLength(); i++) {
				Element libroTag = (Element) libroTagList.item(i);
				Libro libro = new Libro();
				libro.setAutores(new ArrayList<>());
				libro.setEdiciones(new ArrayList<>());
				libros.add(libro);
				Integer isbn = Integer.parseInt(libroTag.getAttribute("isbn"));
				libro.setIsbn(isbn);

				Element tituloTag = (Element) libroTag.getElementsByTagName("titulo").item(0);
				String titulo = tituloTag.getTextContent();
				libro.setTitulo(titulo);

				Element autoresTag = (Element) libroTag.getElementsByTagName("autores").item(0);

				NodeList autorTagList = autoresTag.getElementsByTagName("autor");
				for (int j = 0; j < autorTagList.getLength(); j++) {
					Element autorTag = (Element) autorTagList.item(j);
					String autor = autorTag.getTextContent();
					libro.getAutores().add(autor);
				}

				Element edicionesTag = (Element) libroTag.getElementsByTagName("ediciones").item(0);

				NodeList edicionTagList = edicionesTag.getElementsByTagName("edicion");
				for (int j = 0; j < edicionTagList.getLength(); j++) {
					Element edicionTag = (Element) edicionTagList.item(j);
					Element añoTag = (Element) edicionTag.getElementsByTagName("a�o").item(0);
					Integer año = Integer.parseInt(añoTag.getTextContent());
					Element editorialTag = (Element) edicionTag.getElementsByTagName("editorial").item(0);
					String editorial = editorialTag.getTextContent();

					Edicion edicion = new Edicion();
					edicion.setAño(año);
					edicion.setEditorial(editorial);
					libro.getEdiciones().add(edicion);
				}
			}
			log.info("Importación completada. Total libros obtenidos: " + libros.size());
			return libros;
		} catch (FileNotFoundException e) {
			log.error("El fichero indicado no existe o no se puede leer" , e);
			throw new XMLImportException("El fichero indicado no existe o no se puede leer", e);
		} catch (Exception e) {
			log.error("Error leyendo XML de libros " , e);
			throw new XMLImportException("Error leyendo XML de libros", e);
		}
	}
}
