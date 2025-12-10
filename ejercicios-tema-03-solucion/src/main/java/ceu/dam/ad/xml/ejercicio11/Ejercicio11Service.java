package ceu.dam.ad.xml.ejercicio11;

import java.util.List;

import ceu.dam.ad.xml.ejercicio11.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLExportException;

public interface Ejercicio11Service {

	public void escribirJsonLibros(String pathFile, List<Libro> lista) throws XMLExportException;

	public List<Libro> leerJsonLibros(String pathFile) throws XMLExportException;
	
	public void escribirYamlLibros(String pathFile, List<Libro> lista) throws XMLExportException;

	public List<Libro> leerYamlLibros(String pathFile) throws XMLExportException;

}