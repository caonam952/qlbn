package com.caonam.qlbn;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QlbnApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(QlbnApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null, "ADMIN"));
//            userService.saveRole(new Role(null, "USER"));
//
//            userService.saveUser(new User(null, "Cao Nam", "caonam", "123", new ArrayList<>()));
//            userService.saveUser(new User(null, "Vu Hong", "vuhong", "123", new ArrayList<>()));
//            userService.saveUser(new User(null, "Pham Kim", "phamkim", "123", new ArrayList<>()));
//
//            userService.addRoleToUser("caonam", "ADMIN");
//            userService.addRoleToUser("vuhong", "USER");
//            userService.addRoleToUser("phamkim", "ADMIN");
//        };
//    }

}
