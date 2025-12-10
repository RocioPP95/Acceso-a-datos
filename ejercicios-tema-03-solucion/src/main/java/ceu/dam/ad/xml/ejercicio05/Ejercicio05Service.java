package ceu.dam.ad.xml.ejercicio05;

import java.util.List;

import ceu.dam.ad.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public interface Ejercicio05Service {

	public List<Libro> importXML(String pathFile) throws XMLImportException;

}