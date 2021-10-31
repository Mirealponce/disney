package com.movies.disney.service.implementation;

import com.movies.disney.model.Genero;
import com.movies.disney.repository.GeneroRepository;
import com.movies.disney.service.interfaces.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {
    @Autowired
    private GeneroRepository generoRepository;
    @Override
    public Genero saveGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    public List<Genero> getGenero() {
        return generoRepository.findAll();
    }


    @Override
    public void deleteGenero(Long id) {
        generoRepository.deleteById(id);
    }

    @Override
    public Genero findGeneroById(Long id) {
        return generoRepository.findById(id).orElse(null);
    }
}
