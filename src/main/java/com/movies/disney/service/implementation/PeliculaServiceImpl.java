package com.movies.disney.service.implementation;

import com.movies.disney.model.Pelicula;
import com.movies.disney.repository.PeliculaRepository;
import com.movies.disney.service.interfaces.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Override
    public Pelicula savePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public List<Pelicula> getPeliculas() {
        return peliculaRepository.findAll();
    }



    @Override
    public void deletePelicula(Long id) {
       peliculaRepository.deleteById(id);
    }

    @Override
    public Pelicula findPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pelicula> findBytitulo(String titulo) {
        return peliculaRepository.findBytitulo(titulo);
    }
}
