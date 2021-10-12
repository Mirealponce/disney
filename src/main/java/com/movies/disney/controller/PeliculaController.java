package com.movies.disney.controller;

import com.movies.disney.model.Genero;
import com.movies.disney.model.Pelicula;
import com.movies.disney.service.interfaces.GeneroService;
import com.movies.disney.service.interfaces.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private GeneroService generoService;


    @RequestMapping(value = "/movies/create/{idgen}")
    @PostMapping
    public String createPelicula (@RequestBody Pelicula pelicula, @PathVariable("idgen") long idgen){

        Genero genero=generoService.findGeneroById(idgen);
        if(genero!=null) {

             peliculaService.savePelicula(
                    new Pelicula(pelicula.getImagen(),
                            pelicula.getTitulo(),
                            pelicula.getCalificacion(),
                            genero)
            );
            return "Pelicula creada";
        }
        return "Error";


    }


    @RequestMapping(value = "/movies/edit/{id}")
    @PutMapping
    public String editarPelicula(@RequestBody Pelicula pelicula, @PathVariable("id") long id) {


        Pelicula peliculaAntigua =  peliculaService.findPeliculaById(id);
        if(peliculaAntigua!=null) {
            peliculaAntigua.setTitulo(pelicula.getTitulo());
            peliculaAntigua.setImagen(pelicula.getImagen());
            peliculaAntigua.setFechaCreacion(pelicula.getFechaCreacion());
            peliculaAntigua.setCalificacion(pelicula.getCalificacion());


            peliculaService.savePelicula(peliculaAntigua);
            return "Pelicula Editada";
        }
        return "Error";
    }

    @RequestMapping(value = "/movies/delete/{id}")
    @DeleteMapping
    public String eliminarPelicula(@PathVariable("id") long id){

        peliculaService.deletePelicula(id);
        return "Pelicula Eliminada";
    }

    @RequestMapping(value = "/movies", params = {"name"})
    @GetMapping
    public List<Pelicula> peliculaPorNombre(@RequestParam("name") String name){
        return peliculaService.findBytitulo(name);
    }



    //Método para buscar por genero
    @RequestMapping(value = "/movies", params = {"genre"})
    @GetMapping
    public List<Pelicula> peliculaPorIdGenero(@RequestParam ("genre") Long genre){

        List<Pelicula> listaPeliculas=peliculaService.getPeliculas();
        return listaPeliculas.stream().filter(pelicula -> pelicula.getIdGenero().getIdGenero()==genre).collect(Collectors.toList());
    }


    @RequestMapping(value = "/movies")
    @GetMapping
    public List<Map<String, Object>> listaPeliculas(){
        List<Pelicula> listadoPeliculas = peliculaService.getPeliculas();

        return listadoPeliculas.stream().map(pelicula ->recorrerPeliculas(pelicula)).collect(Collectors.toList());
    }
    public Map<String, Object> recorrerPeliculas(Pelicula pelicula){
        Map<String, Object> mapa = new LinkedHashMap<>();
        mapa.put("imagen", pelicula.getImagen());
        mapa.put("titulo", pelicula.getTitulo());
        mapa.put("fecha", pelicula.getFechaCreacion().toString());
        return mapa;
    }


    @RequestMapping(value = "/movies/detallePelicula")
    @GetMapping
    public List<Map<String, Object>> detallePeliculas(){
        List<Pelicula> listapeliculas = peliculaService.getPeliculas();
        return listapeliculas.stream().map(pelicula -> mapaPelicula(pelicula) ).collect(Collectors.toList());
    }

    public Map<String, Object> mapaPelicula(Pelicula pelicula){
        Map<String, Object> mapPeliculas = new LinkedHashMap<>();
        mapPeliculas.put("imagen",pelicula.getImagen());
        mapPeliculas.put("titulo", pelicula.getTitulo());
        mapPeliculas.put("fecha",pelicula.getFechaCreacion());
        mapPeliculas.put("calificacion",pelicula.getCalificacion());
        mapPeliculas.put("Personajes",pelicula.getPeliculaPersonaje().stream().map(peliculaPersonaje -> peliculaPersonaje.getIdPersonaje().getName()).collect(Collectors.toList()));
        return mapPeliculas;
    }



}
