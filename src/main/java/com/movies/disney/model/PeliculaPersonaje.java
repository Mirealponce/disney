package com.movies.disney.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PeliculaPersonaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native",strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPelicula")
    @JsonIgnore
    private Pelicula idPelicula;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personajeId")
    @JsonIgnore
    private Personaje idPersonaje;


    public PeliculaPersonaje(Pelicula idPelicula, Personaje idPersonaje) {
        this.idPelicula = idPelicula;
        this.idPersonaje = idPersonaje;
    }
}
