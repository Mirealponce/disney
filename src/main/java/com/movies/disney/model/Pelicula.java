package com.movies.disney.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native",strategy = "native")
    private long idPelicula;

    private String imagen;
    private String titulo;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    private int calificacion;

    @OneToMany(mappedBy = "idPelicula",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PeliculaPersonaje> peliculaPersonaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGenero")
    @JsonIgnore
    private Genero idGenero;



    public Pelicula( String imagen, String titulo, int calificacion, Genero idGenero) {

        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaCreacion = new Date();
        this.calificacion = calificacion;
        this.idGenero=idGenero;
    }

}
