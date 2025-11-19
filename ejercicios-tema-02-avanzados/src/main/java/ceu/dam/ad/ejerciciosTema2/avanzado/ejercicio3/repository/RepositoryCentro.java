package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.CentroComercial;

public interface RepositoryCentro extends JpaRepository<CentroComercial, UUID> {
}