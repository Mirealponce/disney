package com.movies.disney.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native",strategy = "native")
    private long idPersonaje;
    private String imagen;
    private String name;
    private int age;
    private double peso;
    private String historia;

    @OneToMany(mappedBy = "idPersonaje",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PeliculaPersonaje> peliculaPersonaje;


    public Personaje(String imagen, String name, int age, double peso, String historia) {
        this.imagen = imagen;
        this.name = name;
        this.age = age;
        this.peso = peso;
        this.historia = historia;
    }

}
