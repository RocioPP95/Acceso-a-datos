package ceu.dam.ad.castillo.service;

import java.util.List;

import ceu.dam.ad.castillo.model.Castillo;

public interface CastilloXmlService {

	void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException;

	
	Castillo importCastilloFromXml(String fileName) throws CastilloXmlException;


	void exportCastilloToXmlJackson(String fileName, Castillo castillo) throws CastilloXmlException;


	Castillo importCastilloToXmlJackson(String fileName) throws CastilloXmlException;


	void exportCastillosToXmlJackson(String fileName, List<Castillo> castillos) throws CastilloXmlException;

}