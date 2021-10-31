package com.movies.disney.controller;

import com.movies.disney.model.Personaje;
import com.movies.disney.model.Usuario;
import com.movies.disney.repository.UserRepository;
import com.movies.disney.security.PasswordEncode;
import com.movies.disney.service.interfaces.PersonajeService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/auth/register")
    @PostMapping
    public ResponseEntity <?> register(@RequestBody Usuario usuario) throws IOException {
        if( usuario.getUsername().isEmpty() || usuario.getPassword().isEmpty()){
            return new ResponseEntity<>("Datos no ingresados", HttpStatus.FORBIDDEN);
        }

        if(userRepository.findByUsername(usuario.getUsername())!=null){
            return new ResponseEntity<>("Ya existe un usuario con ese correo", HttpStatus.FORBIDDEN);
        }

        Usuario user=userRepository.save(new Usuario(usuario.getUsername(),passwordEncoder.encode(usuario.getPassword())));

        this.sendEmail(user.getUsername());
        return new ResponseEntity<>("Usuario creado: "+ user.getUsername(), HttpStatus.CREATED);


    }

    private void sendEmail(String destinatario) throws IOException {

        Email from = new Email("mirealponce1407@gmail.com");
        String subject = "Hola, "+from+  " !";

        Email to = new Email(destinatario);
        Content content = new Content("text/plain", "Bienvenido al nuevo servicio de streaming");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        System.out.println(sg);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
