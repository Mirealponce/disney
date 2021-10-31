package com.movies.disney;

import com.movies.disney.model.Usuario;
import com.movies.disney.repository.UserRepository;
import com.movies.disney.security.Config;
import com.movies.disney.security.PasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DisneyApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(DisneyApplication.class, args);

	}
	PasswordEncode passwordEncode;
	@Bean
	public CommandLineRunner datos(UserRepository userRepository){
		return args -> {
			Usuario usuario= new Usuario("mirealponce1407@gmail.com",passwordEncoder().encode("123"));
			userRepository.save(usuario);
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
