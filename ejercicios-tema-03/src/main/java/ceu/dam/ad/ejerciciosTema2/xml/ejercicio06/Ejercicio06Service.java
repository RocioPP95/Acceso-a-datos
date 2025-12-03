package ceu.dam.ad.ejerciciosTema2.xml.ejercicio06;

import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLExportException;

public interface Ejercicio06Service {

	public void exportarXML(List<Largometraje> peliculas, String fichero) throws XMLExportException;

}