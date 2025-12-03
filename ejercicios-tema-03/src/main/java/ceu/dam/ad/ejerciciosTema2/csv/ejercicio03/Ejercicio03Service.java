package ceu.dam.ad.ejerciciosTema2.csv.ejercicio03;

import ceu.dam.ad.ejerciciosTema2.csv.exceptions.CsvException;

public interface Ejercicio03Service {

	void convertirCsv(String ficheroEntrada, String ficheroSalida) throws CsvException;

}