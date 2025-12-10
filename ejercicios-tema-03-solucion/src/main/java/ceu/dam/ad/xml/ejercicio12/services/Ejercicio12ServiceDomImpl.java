package ceu.dam.ad.xml.ejercicio12.services;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

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
import org.w3c.dom.NodeList;

import ceu.dam.ad.xml.ejercicio12.modelo.Articulo;
import ceu.dam.ad.xml.ejercicio12.modelo.Catalogo;
import ceu.dam.ad.xml.ejercicio12.modelo.Categoria;
import ceu.dam.ad.xml.ejercicio12.modelo.Modelo;
import ceu.dam.ad.xml.ejercicio12.modelo.Pvp;

public class Ejercicio12ServiceDomImpl implements  Ejercicio12Service{

	private static final Logger log = LoggerFactory.getLogger(Ejercicio12ServiceDomImpl.class);
	
	public void exportXML(Catalogo catalogo, String pathFile) throws CatalogoXMLException {
		try {
			log.info("Exportando catálogo a XML " + pathFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document xml = builder.newDocument();

			Element catalogoTag = xml.createElement("catálogo");
			catalogoTag.setAttribute("tamaño", catalogo.getTamaño().toString());
			xml.appendChild(catalogoTag);

			Element articulosTag = xml.createElement("articulos");
			catalogoTag.appendChild(articulosTag);

			// artículos
			for (Articulo articulo : catalogo.getArticulos()) {
				log.debug("Registrando nuevo artículo en el XML: " + articulo);
				Element articuloTag = xml.createElement("articulo");
				articulosTag.appendChild(articuloTag);

				Element descripcionTag = xml.createElement("descripcion");
				descripcionTag.setTextContent(articulo.getDescripcion());
				articuloTag.appendChild(descripcionTag);

				Element identificadorTag = xml.createElement("identificador");
				identificadorTag.setTextContent(articulo.getSku());
				articuloTag.appendChild(identificadorTag);

				Element precioTag = xml.createElement("precio");
				precioTag.setTextContent(articulo.getPvp().getPrecio().toString());
				articuloTag.appendChild(precioTag);

				// modelos
				Element modelosTag = xml.createElement("modelosDisponibles");
				articuloTag.appendChild(modelosTag);
				for (Modelo modelo : articulo.getModelos()) {
					Element modeloTag = xml.createElement("modelo");
					modelosTag.appendChild(modeloTag);

					Element tallaTag = xml.createElement("talla");
					tallaTag.setTextContent(modelo.getTalla());
					modeloTag.appendChild(tallaTag);

					Element colorTag = xml.createElement("color");
					colorTag.setTextContent(modelo.getColor());
					modeloTag.appendChild(colorTag);

					Element codigosTag = xml.createElement("codigosBarra");
					modeloTag.appendChild(codigosTag);

					// códigos de barra
					for (String codigo : modelo.getCodigosBarra()) {
						Element codigoTag = xml.createElement("codigo");
						codigoTag.setTextContent(codigo);
						codigosTag.appendChild(codigoTag);
					}
				}

				// categorías
				Element categoriasTag = xml.createElement("categorias");
				articuloTag.appendChild(categoriasTag);
				for (Categoria categoria : articulo.getCategorias()) {
					Element categoriaTag = xml.createElement("categoria");
					categoriaTag.setTextContent(categoria.getNombre());
					categoriasTag.appendChild(categoriaTag);
				}

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(xml);
			StreamResult sr = new StreamResult(new File(pathFile));
			transformer.transform(source, sr);
			log.info("Catálogo exportado con éxito.");

		} catch (Exception e) {
			log.error("Error al exportar catálogo", e);
			throw new CatalogoXMLException("Error al exportar catálogo", e);
		}

	}

	public Catalogo importXML(String pathFile) throws CatalogoXMLException {
		try {
			log.info("Importando fichero XML de catálogo de artículos " + pathFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Obtener XML a partir de la ruta
			File file = new File(pathFile);
			Document xml = builder.parse(file);

			// Obtengo el root
			Element catalogoTag = xml.getDocumentElement();
			Catalogo catalogo = new Catalogo();
			catalogo.setTamaño(Integer.parseInt(catalogoTag.getAttribute("tamaño")));
			catalogo.setArticulos(new ArrayList<Articulo>()); // inicializamos si no está ya en el constructor

			// Articulos
			Element articulosTag = (Element) catalogoTag.getElementsByTagName("articulos").item(0);
			NodeList articuloTagList = articulosTag.getElementsByTagName("articulo");
			for (int i = 0; i < articuloTagList.getLength(); i++) {
				Element articuloTag = (Element) articuloTagList.item(i);
				Articulo articulo = new Articulo();
				articulo.setCategorias(new ArrayList<>());// inicializamos si no está ya en el constructor
				articulo.setModelos(new ArrayList<>());// inicializamos si no está ya en el constructor
				catalogo.getArticulos().add(articulo);

				Element descripTag = (Element) articuloTag.getElementsByTagName("descripcion").item(0);
				articulo.setDescripcion(descripTag.getTextContent());

				Element identificadorTag = (Element) articuloTag.getElementsByTagName("identificador").item(0);
				articulo.setSku(identificadorTag.getTextContent());

				Element precioTag = (Element) articuloTag.getElementsByTagName("precio").item(0);
				Pvp pvp = new Pvp();
				pvp.setPrecio(new BigDecimal(precioTag.getTextContent()));
				articulo.setPvp(pvp);

				// Modelos
				Element modelosTag = (Element) articuloTag.getElementsByTagName("modelosDisponibles").item(0);
				NodeList modeloTagList = modelosTag.getElementsByTagName("modelo");
				for (int j = 0; j < modeloTagList.getLength(); j++) {
					Element modeloTag = (Element) modeloTagList.item(j);
					Modelo modelo = new Modelo();
					modelo.setCodigosBarra(new ArrayList<String>());// inicializamos si no está ya en el constructor
					articulo.getModelos().add(modelo);
					
					Element tallaTag = (Element) modeloTag.getElementsByTagName("talla").item(0);
					modelo.setTalla(tallaTag.getTextContent());

					Element colorTag = (Element) modeloTag.getElementsByTagName("color").item(0);
					modelo.setColor(colorTag.getTextContent());

					// Códigos de barraa
					Element codigosTag = (Element) modeloTag.getElementsByTagName("codigosBarra").item(0);
					NodeList codigoTagList = codigosTag.getElementsByTagName("codigo");
					for (int k = 0; k < codigoTagList.getLength(); k++) {
						Element codigoTag = (Element) codigoTagList.item(k);
						modelo.getCodigosBarra().add(codigoTag.getTextContent());
					}
				}
				
				
				// Categorías
				Element categoriasTag = (Element) articuloTag.getElementsByTagName("categorias").item(0);
				NodeList categoriaTagList = categoriasTag.getElementsByTagName("categoria");
				for (int j = 0; j < categoriaTagList.getLength(); j++) {
					Element categoriaTag = (Element) categoriaTagList.item(j);
					Categoria categoria = new Categoria();
					categoria.setNombre(categoriaTag.getTextContent());
					articulo.getCategorias().add(categoria);
				}
				
			}

			log.info("Importación completada. Total artículos obtenidos: " + catalogo.getArticulos().size());
			return catalogo;
		} catch (Exception e) {
			log.error("Error importando catálogo", e);
			throw new CatalogoXMLException("Error al importar catálogo", e);
		}
	}

}
