package ceu.dam.ad.xml.ejercicio07;

import java.util.List;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public interface Ejercicio07Service {

	public List<Largometraje> importarXML(String fichero) throws XMLImportException;

}