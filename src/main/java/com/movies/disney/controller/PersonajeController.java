package com.movies.disney.controller;

import com.movies.disney.model.Pelicula;
import com.movies.disney.model.PeliculaPersonaje;
import com.movies.disney.model.Personaje;
import com.movies.disney.service.interfaces.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;
    @RequestMapping(value = "/characters/create")
    @PostMapping
    public Personaje createPersonaje(@RequestBody Personaje personaje){

        return personajeService.savePersonaje(personaje);

    }


    @RequestMapping(value = "/characters/edit/{id}")
    @PutMapping
    public String editarPersonaje (@RequestBody Personaje personaje,@PathVariable("id") long id){

        Optional<Personaje> personajemod=personajeService.findPersonajeById(id);

        if (personajemod.isPresent()){
            Personaje personajeantiguo= personajemod.get();
            personajeantiguo.setName(personaje.getName());
            personajeantiguo.setImagen(personaje.getImagen());
            personajeantiguo.setAge(personaje.getAge());
            personajeantiguo.setPeso(personaje.getPeso());
            personajeantiguo.setHistoria(personaje.getHistoria());

            personajeService.savePersonaje(personajeantiguo);
        }
        return "personaje modificado";
    }
    @RequestMapping(value = "/characters/delete/{id}")
    @DeleteMapping
    public String deletePersonaje (@PathVariable("id") long id){

        personajeService.deletePersonaje(id);
        return "Personaje eliminado";
    }


    @RequestMapping(value = "/characters", params = {"name"})
    @GetMapping
    public List<Personaje> mostrarPersonaje(@RequestParam ("name") String name){
        //System.out.println(name);
        //System.out.println(repositoryPersonaje.findByName(name));
        return personajeService.findByName(name);
    }


    @RequestMapping(value = "/characters" , params = {"movies"})
    @GetMapping
    public List<List<PeliculaPersonaje>> personajeById(@RequestParam ("movies") Long movies){
        return personajeService.getPersonajes().stream().map(personaje -> personaje.getPeliculaPersonaje().stream().filter(peliculaPersonaje -> peliculaPersonaje.getIdPelicula().getIdPelicula()==movies).collect(Collectors.toList())).collect(Collectors.toList());
    }


    @RequestMapping(value = "/characters",params = {"age"})
    @GetMapping
    public List<Personaje> mostrarPersonaje(@RequestParam ("age") int age){

        return personajeService.findByAge(age);
    }

    @RequestMapping(value = "/characters")
    @GetMapping
    public List<Map<String,Object>> getPersonaje(){

        List<Personaje> listaPersonajes=personajeService.getPersonajes();

        return  listaPersonajes.stream().map(personaje -> dtoPersonajes(personaje)).collect(Collectors.toList());

    }
    @RequestMapping(value = "/characters/details")
    @GetMapping
    public List<Map<String,Object>>detailsPersonajes(){
        List<Personaje> listaPersonajess= personajeService.getPersonajes();

        return listaPersonajess.stream().map(personaje->dtodetallePersonaje(personaje)).collect(Collectors.toList());
    }



    public Map<String,Object> dtodetallePersonaje(Personaje personaje){

        Map<String,Object> dto= new LinkedHashMap<>();
        dto.put("imagenPersonaje",personaje.getImagen());
        dto.put("namePersonaje",personaje.getName());
        dto.put("age",personaje.getAge());
        dto.put("peso",personaje.getPeso());
        dto.put("historia",personaje.getHistoria());
        dto.put("Pelicula",personaje.getPeliculaPersonaje().stream().map(peliculaPersonaje -> peliculaPersonaje.getIdPelicula().getTitulo()).collect(Collectors.toList()));

        return dto;
    }
    public Map<String,Object> dtoPersonajes(Personaje personaje){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("imagenPersonaje",personaje.getImagen());
        dto.put("namePersonaje",personaje.getName());

        return dto;
    }

}
