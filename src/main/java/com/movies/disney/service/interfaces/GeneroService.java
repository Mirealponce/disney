package com.movies.disney.service.interfaces;
import com.movies.disney.model.Genero;
import java.util.List;

public interface GeneroService {
    Genero saveGenero(Genero genero);
    List<Genero> getGenero();
    Genero updateGenero(Genero genero);
    boolean deleteGenero(Long id);
    Genero findGeneroById(Long id);
}
