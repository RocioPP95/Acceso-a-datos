package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service;

import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;

public interface LibroService {
	 void generarXml(String fileName, List<Libro> libros) throws LibroXmlException;

}
