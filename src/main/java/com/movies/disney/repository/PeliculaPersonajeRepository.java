package com.movies.disney.repository;


import com.movies.disney.model.PeliculaPersonaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaPersonajeRepository extends JpaRepository<PeliculaPersonaje, Long> {
}
