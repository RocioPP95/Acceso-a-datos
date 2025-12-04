package ceu.dam.ad.castillo.service;

import ceu.dam.ad.castillo.model.Castillo;

public interface CastilloXmlService {

	void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException;
	Castillo importtCastilloToXml(String fileName) throws CastilloXmlException;
	

}