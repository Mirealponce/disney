package com.movies.disney.controller;

import com.movies.disney.model.Genero;
import com.movies.disney.model.Pelicula;
import com.movies.disney.service.interfaces.GeneroService;
import com.movies.disney.service.interfaces.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    @RequestMapping(value = "/movies", params = {"order"})
    @GetMapping
    public List<Pelicula> peliculasOrder(@RequestParam ("order") Long order){
        List<Pelicula> listaPeliculas=peliculaService.getPeliculas();
        if(order.equals("DESC")){
            return listaPeliculas.stream().sorted(Comparator.comparing(Pelicula::getFechaCreacion).reversed()).collect(Collectors.toList());

        }else {
            if(order.equals("ASC")){
                return listaPeliculas.stream().sorted(Comparator.comparing(Pelicula::getFechaCreacion)).collect(Collectors.toList());

            }
        }

        return null;

    }


    @RequestMapping(value = "/movies")
    @GetMapping
    public List<Map<String, Object>> listaPeliculas(){
        List<Pelicula> listadoPeliculas = peliculaService.getPeliculas();

        return listadoPeliculas.stream().map(pelicula ->dtoPeliculas(pelicula)).collect(Collectors.toList());
    }
    public Map<String, Object> dtoPeliculas(Pelicula pelicula){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("imagen", pelicula.getImagen());
        dto.put("titulo", pelicula.getTitulo());
        dto.put("fecha", pelicula.getFechaCreacion().toString());
        return dto;
    }


    @RequestMapping(value = "/movies/detallePelicula")
    @GetMapping
    public List<Map<String, Object>> detallePeliculas(){
        List<Pelicula> listapeliculas = peliculaService.getPeliculas();
        return listapeliculas.stream().map(pelicula -> dtoDetailsPelicula(pelicula) ).collect(Collectors.toList());
    }

    public Map<String, Object> dtoDetailsPelicula(Pelicula pelicula){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("imagen",pelicula.getImagen());
        dto.put("titulo", pelicula.getTitulo());
        dto.put("fecha",pelicula.getFechaCreacion());
        dto.put("calificacion",pelicula.getCalificacion());
        dto.put("Personajes",pelicula.getPeliculaPersonaje().stream().map(peliculaPersonaje -> peliculaPersonaje.getIdPersonaje().getName()).collect(Collectors.toList()));
        return dto;
    }



}
