package ceu.dam.ad.ejerciciosTema2.csv.ejercicio01;

import ceu.dam.ad.ejerciciosTema2.csv.exceptions.CsvException;

public interface Ejercicio01Service {

	public void exportarPeliculasCsv(String nombreFichero) throws CsvException;

}