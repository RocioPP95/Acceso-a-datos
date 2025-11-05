package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Trailer;

@Repository

//siempre extiende de JpaRepository
//perro es la entiti
public interface RepositoryTrailer extends JpaRepository<Trailer, Long> {

}
