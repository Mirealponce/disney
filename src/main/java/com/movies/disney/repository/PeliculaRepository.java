package com.movies.disney.repository;
import com.movies.disney.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findBytitulo(@Param("titulo") String titulo);
}
