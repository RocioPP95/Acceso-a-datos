package ceu.dam.ad.xml.ejercicio04;

import java.util.List;

import ceu.dam.ad.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public interface Ejercicio04Service {

	public void exportXML(List<Libro> libros, String pathFile) throws XMLExportException;

}