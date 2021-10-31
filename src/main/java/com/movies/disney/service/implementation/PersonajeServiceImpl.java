package com.movies.disney.service.implementation;

import com.movies.disney.model.Personaje;
import com.movies.disney.repository.PersonajeRepository;
import com.movies.disney.service.interfaces.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    @Autowired
    private PersonajeRepository personajeRepository;
    @Override
    public Personaje savePersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    @Override
    public List<Personaje> getPersonajes() {
        return personajeRepository.findAll();
    }


    @Override
    public void deletePersonaje(Long id) {
         personajeRepository.deleteById(id);
    }

    @Override
    public Optional<Personaje> findPersonajeById(Long id) {
        return personajeRepository.findById(id);
    }

    @Override
    public List<Personaje> findByName(String name) {
        return personajeRepository.findByName(name);
    }

    @Override
    public List<Personaje> findByAge(int age) {
        return personajeRepository.findByAge(age);
    }
}
