package com.movies.disney.repository;
import com.movies.disney.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findByName(@Param("name") String name);

    List<Personaje> findByAge(@Param("age") int age);
}
