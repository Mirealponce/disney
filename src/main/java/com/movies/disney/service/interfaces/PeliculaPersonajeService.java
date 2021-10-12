package com.movies.disney.service.interfaces;
import com.movies.disney.model.PeliculaPersonaje;
import java.util.List;

public interface PeliculaPersonajeService  {
    PeliculaPersonaje savePeliculaPersonaje(PeliculaPersonaje peliculaPersonaje);
    List<PeliculaPersonaje> getPeliculasPersonajes();
    PeliculaPersonaje updatePeliculaPersonaje(PeliculaPersonaje peliculaPersonaje);
    boolean deletePeliculaPersonaje(Long id);
    PeliculaPersonaje findPeliculaPersonajeById(Long id);
}
