package ceu.dam.ad.ejerciciosTema2.xml.ejercicio10;

import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLExportException;

public interface Ejercicio10Service {

	public void escribirXMLLibros(String pathFile, List<Libro> lista) throws XMLExportException;

	public List<Libro> leerXMLLibros(String pathFile) throws XMLExportException;


}