package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service;

import java.io.File;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;

public class LibroServiceImpl implements LibroService {

	private static final Logger logger = LoggerFactory.getLogger(LibroServiceImpl.class);

	@Override
	public void generarXml(String fileName, List<Libro> libros) throws LibroXmlException {

		  logger.info("Generando fichero XML:", fileName);
		try {
			// Crear documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			// Rellenar

			// Nodo raíz <libros>
			Element root = document.createElement("libros");
			document.appendChild(root);

			for (Libro libro : libros) {
				
                logger.debug("Incluyendo libro en XML");

				// <libro isbn="...">
				Element libroElement = document.createElement("libro");
				libroElement.setAttribute("isbn", String.valueOf(libro.getIsbn()));
				root.appendChild(libroElement);

				// <titulo>
				Element titulo = document.createElement("titulo");
				titulo.setTextContent(libro.getTitulo());
				libroElement.appendChild(titulo);

				// <autores>
				Element autoresElem = document.createElement("autores");
				libroElement.appendChild(autoresElem);

				for (String autor : libro.getAutores()) {
					Element autorElem = document.createElement("autor");
					autorElem.setTextContent(autor);
					autoresElem.appendChild(autorElem);
				}

				// <ediciones>
				Element edicionesElem = document.createElement("ediciones");
				libroElement.appendChild(edicionesElem);

				libro.getEdiciones().forEach(ed -> {
					Element edicionElem = document.createElement("edicion");

					Element añoElement = document.createElement("año");
					añoElement.setTextContent(String.valueOf(ed.getAño()));
					edicionElem.appendChild(añoElement);

					Element editorialElem = document.createElement("editorial");
					editorialElem.setTextContent(ed.getEditorial());
					edicionElem.appendChild(editorialElem);

					edicionesElem.appendChild(edicionElem);
				});
			}

			// Exportar documento a fichero
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			File file = new File(fileName);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);

			 logger.info("Fichero XML generado correctamente en:", fileName);

		} catch (Exception e) {
			logger.error("Error generando XML en el fichero", fileName, e);
			throw new LibroXmlException("Error generando XML", e);
		}

	}

}
