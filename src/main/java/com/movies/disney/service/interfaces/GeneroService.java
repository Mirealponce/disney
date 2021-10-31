package com.movies.disney.service.interfaces;
import com.movies.disney.model.Genero;
import java.util.List;

public interface GeneroService {
    Genero saveGenero(Genero genero);
    List<Genero> getGenero();
    void deleteGenero(Long id);
    Genero findGeneroById(Long id);
}
