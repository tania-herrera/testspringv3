package org.core.curso.testspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableWebSecurity
@EnableMethodSecurity(
	 	prePostEnabled = true, 
	 	securedEnabled = true, 
	 	jsr250Enabled = true)
@Slf4j
public class WebSecurityConfig {
	
//	@Autowired
//	private IUserService userService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// Content to the basic autoconfiguration of Spring Security.
		// Spring takes access control with html pages by default for login and logout
		// but it can be changed for our own forms.
        httpSecurity
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) //Necesario para que salgan todos los marcos de /h2
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**")) // Para permitir acceso a /h2/** sin CSRF
                .authorizeHttpRequests((authorize) -> authorize
			        	.requestMatchers("/", "/index", "/login*", "/h2/**").permitAll()
			        	.requestMatchers("/img/**").permitAll()
			        	.requestMatchers("/loginPost").authenticated()
                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());	
        
                .formLogin(formLogin -> {
                    formLogin
                      .loginPage("/loginGet") // Login page will be accessed through this endpoint. We will create a controller method for this.
                      //.loginProcessingUrl("/login-processing") // This endpoint will be mapped internally. This URL will be our Login form post action.
                      //.usernameParameter("username")
                      //.passwordParameter("password")
                      .permitAll() // We re permitting all for login page
                      .defaultSuccessUrl("/master") // If the login is successful, user will be redirected to this URL.
                      .failureUrl("/login?badCredentials"); // If the user fails to login, application will redirect the user to this endpoint
                   	})
                .logout(formLogout -> {
                    formLogout
                    .logoutUrl("/logoutGet")
                    .permitAll() // We re permitting all for logout page
                    .logoutSuccessUrl("/loginGet?logoutOk"); // If the logout is successful, user will be redirected to this URL.
                	})
                	
                .sessionManagement(session -> session.maximumSessions(2))
                ;
        
        log.info("*********** httpSecurity.getSharedObjects()= " + httpSecurity.getSharedObjects());

		return httpSecurity.build();
	}

	/*
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity) 
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder =
			httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);		
		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());		
		return authenticationManagerBuilder.build();
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider =
				new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userService);
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoderBCrypt());
		return daoAuthenticationProvider;
	}
	
	// Beans for data encryption.
	@Bean
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
	 */

}

/*
    // Para que la petición de credenciales se haga desde el formulario de Spring
	@Bean
	UserDetailsService userDetailsService() {
		// Para utilizar nuestro propio UserServiceImpl extendiendo el UserDetailsService 
		// por defecto de Spring-Security:
		return new UserServiceImpl(passwordEncoderBCrypt(), new User(), new String(), userRepository);
		
		// Para utilizar el UserDetailsService por defecto de Spring-Security:
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(User.withUsername("ana")
	      .password(passwordEncoder.encode("anaPasss"))
	      .roles("USER")
	      .build());
	    manager.createUser(User.withUsername("pepe")
	      .password(passwordEncoder.encode("pepePass"))
	      .roles("USER", "ADMIN")
	      .build());
	    manager.createUser(User.withUsername("luis")
	  	  .password(passwordEncoder.encode("luisPass"))
	  	  .roles("ADMIN")
	  	  .build());
	    return manager;
	}
 */

/*
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	// Content to the basic autoconfiguration of Spring Security.
	// Spring takes access control with html pages by default for login and logout
	// but it can be changed for our own forms.
    httpSecurity
            .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) //Necesario para que salgan todos los marcos de /h2
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**")) // Para permitir acceso a /h2/** sin CSRF
            .authorizeHttpRequests((authorize) -> authorize
		        	.requestMatchers("/", "/login*", "/h2/**").permitAll()
		        	.requestMatchers("/static/**").permitAll()
		        	.requestMatchers("/index").authenticated()
//		        	.requestMatchers("/img/**").permitAll()
//		        	.requestMatchers("/welcome").authenticated()
//                   .requestMatchers("/contacts").hasAnyRole("USER")
                    .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults());	
//            .formLogin(formLogin -> {
//                formLogin
//                  .loginPage("/login") // Login page will be accessed through this endpoint. We will create a controller method for this.
//                  //.loginProcessingUrl("/login-processing") // This endpoint will be mapped internally. This URL will be our Login form post action.
//                  //.usernameParameter("username")
//                  //.passwordParameter("password")
//                  .permitAll() // We re permitting all for login page
//                  .defaultSuccessUrl("/welcome") // If the login is successful, user will be redirected to this URL.
//                  .failureUrl("/login?badCredentials"); // If the user fails to login, application will redirect the user to this endpoint
//               })
//            .logout(formLogout -> {
//                formLogout
//                .logoutUrl("/logout")
//                .permitAll() // We re permitting all for logout page
//                .logoutSuccessUrl("/index?logoutOk"); // If the logout is successful, user will be redirected to this URL.
//            }
    		;

	return httpSecurity.build();
}
*/
