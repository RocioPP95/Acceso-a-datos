package ceu.dam.ad.ejerciciosTema2.csv.ejercicio02;

import ceu.dam.ad.ejerciciosTema2.csv.exceptions.CsvException;

public interface Ejercicio02Service {

	public void importarUsuarioCSV(String nombreFichero) throws CsvException;

}