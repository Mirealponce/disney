package com.movies.disney.service.implementation;

import com.movies.disney.model.PeliculaPersonaje;
import com.movies.disney.service.interfaces.PeliculaPersonajeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaPersonajeServiceImpl implements PeliculaPersonajeService {
    @Override
    public PeliculaPersonaje savePeliculaPersonaje(PeliculaPersonaje peliculaPersonaje) {
        return null;
    }

    @Override
    public List<PeliculaPersonaje> getPeliculasPersonajes() {
        return null;
    }

    @Override
    public PeliculaPersonaje updatePeliculaPersonaje(PeliculaPersonaje peliculaPersonaje) {
        return null;
    }

    @Override
    public boolean deletePeliculaPersonaje(Long id) {
        return false;
    }

    @Override
    public PeliculaPersonaje findPeliculaPersonajeById(Long id) {
        return null;
    }
}
