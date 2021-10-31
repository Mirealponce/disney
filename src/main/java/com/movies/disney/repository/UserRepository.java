package com.movies.disney.repository;

import com.movies.disney.model.Pelicula;
import com.movies.disney.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(@Param("username") String username);

}
