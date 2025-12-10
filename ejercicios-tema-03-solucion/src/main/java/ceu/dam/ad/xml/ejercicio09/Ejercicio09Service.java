package ceu.dam.ad.xml.ejercicio09;

import java.util.List;

import ceu.dam.ad.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.xml.exceptions.XMLImportException;

public interface Ejercicio09Service {

	public List<Largometraje> importXML(String pathFile) throws XMLImportException;

}