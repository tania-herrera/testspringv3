package org.core.curso.testspring.config;

import org.core.curso.testspring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import org.core.curso.testspring.service.IUserService;

//import com.core.timmyProfe.data.repository.IUserRepository;
//import com.core.timmyProfe.service.IUserService;
//import com.core.timmyProfe.serviceImpl.UserServiceImpl;
//import com.core.timmyProfe.service.IUserService;

import lombok.extern.slf4j.Slf4j;


/*
 * Webs to encrypt with BCrypt algorithm:
 * https://bcrypt-generator.com/
 * https://www.devglan.com/online-tools/bcrypt-hash-generator
 */
@Configuration

@Slf4j
public class PasswordEncoderConfig {

	
	
	// Beans for data encryption.
	
	@Bean
	@Primary
	PasswordEncoder passwordEncoderBCrypt() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	PasswordEncoder passwordEncoderSCrypt() {
		return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();  
		// Don's use constructor, it needs several parameters.
		// Instead use method defaultsForSpringSecurity_v5_8() that
		// constructs a SCrypt password encoder with cpu cost of 65,536, 
		// memory cost of 8,parallelization of 1, a key length of 32 and 
		// a salt length of 16 bytes.
	}
	
	@Bean
	PasswordEncoder passwordEncoderPbkdf2() {
		return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8(); //new Pbkdf2PasswordEncoder();
		// Don's use constructor, it needs several parameters.
		// Instead use method defaultsForSpringSecurity_v5_8() that
		// constructs a PBKDF2 password encoder with no additional secret value. 
		// There will be a salt length of 16 bytes, 310,000 iterations, SHA-256 algorithm 
		// and a hash length of 256 bits. The default is based upon aiming for .5 seconds 
		// to validate the password when this class was added. Users should tune 
		// password verification to their own systems.
	}

}

