package com.movies.disney.service.interfaces;

import com.movies.disney.model.Personaje;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonajeService {

    Personaje savePersonaje(Personaje personaje);
    List<Personaje> getPersonajes();
    void deletePersonaje(Long id);
    Optional<Personaje> findPersonajeById(Long id);
    List<Personaje> findByName(String name);
    List<Personaje> findByAge(int age);

}
