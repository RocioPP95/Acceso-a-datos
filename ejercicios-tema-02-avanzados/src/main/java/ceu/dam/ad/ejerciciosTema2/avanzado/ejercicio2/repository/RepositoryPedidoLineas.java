package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.PedidoLinea;

@Repository

public interface RepositoryPedidoLineas extends JpaRepository<PedidoLinea, UUID> {

}
