package com.movies.disney.service.interfaces;
import com.movies.disney.model.Pelicula;
import java.util.List;

public interface PeliculaService {
    Pelicula savePelicula(Pelicula pelicula);
    List<Pelicula> getPeliculas();

    void deletePelicula(Long id);
    Pelicula findPeliculaById(Long id);
    List<Pelicula> findBytitulo( String titulo);
}
