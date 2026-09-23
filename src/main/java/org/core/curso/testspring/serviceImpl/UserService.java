package org.core.curso.testspring.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.core.curso.testspring.config.LoginFailureHandler;
import org.core.curso.testspring.data.model.User;
import org.core.curso.testspring.data.repository.IUserRepository;
import org.core.curso.testspring.service.IRoleService;
import org.core.curso.testspring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

    
	private final IUserRepository repo;
	private final IRoleService roleService;
	private final PasswordEncoder passwordEncoder;
	

	UserService(IUserRepository repo, IRoleService roleService, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public void showUser(Optional<User> userOpt) {
		System.out.println("\t" + (userOpt.isPresent() ? userOpt.get() : "No existe el id dado"));
	}
	
	@Override
	public void showAll() {
		this.findAll().stream()
			.forEach(c -> System.out.println("\t" + c));
	}

	@Override
	public Long count() {
		return repo.count();
	}

	@Override
	public void saveDataTestDev() {
		repo.save(new User("curso", 
				"$2a$12$ZIOUB3aIzuXdQ7RA/DJZr.bM6TcxOO7QuA7/1WADM7n45d7nVXTpO", //fjgklj
				"curso@gmail.com", 
				"Usuario del curso",
				LocalDate.now().plusDays(2),
				false,
				LocalDate.now().plusDays(5),
				true,
				Set.of(roleService.findByRolename("ADMIN").get(),
						roleService.findByRolename("CUSTOMER").get())
				));
		
		repo.save(new User("jag", 
				"$2a$12$xqvz8G5CWU.VLhyXigPn4.B5.TINHXFbHuBmYClHjRJwjVjRD41kW", //jag
				"jag@gmail.com", 
				"José A. Gutiérrez",
				LocalDate.now().plusDays(2),
				false,
				LocalDate.now().plusDays(5),
				true,
				Set.of(roleService.findByRolename("ADMIN").get(),
						roleService.findByRolename("CUSTOMER").get())
				));
		
		repo.save(User.builder()
				.username("ana")
				.email("ana@gmail.com")
				.fullname("Ana Sanz")
				.expiryDateAccount(LocalDate.now().plusDays(1000))
				.expiryDateCredentials(LocalDate.now().plusDays(300))
				.lockedAccount(false)
				.enabled(false)
				.password("$2a$12$dR4tDCQBUFIfmr3.NslvsuGrPpYofax3KnBgAbvRpON6GJ.sSAqce")
				.roleSet(Set.of(roleService.findByRolename("MANAGER").get(),
								roleService.findByRolename("CUSTOMER").get()))
				.build()
				);
		repo.save(User.builder()
				.username("luis")
				.email("luis@gmail.com")
				.fullname("Luis Pérez")
				.expiryDateAccount(LocalDate.now().plusDays(1000))
				.expiryDateCredentials(LocalDate.now().plusDays(1000))
				.lockedAccount(false)
				.enabled(true)
				.password("$2a$12$OOhpBXUplTFhEx/Yp6rDxOWJ7Q75CzyWWROl.P7h2kdawZnDx9UXe")
				.roleSet(Set.of(roleService.findByRolename("MANAGER").get()))
				.build()
				);
		
		//Es buena practica poner un punto en cada línea
				repo.save(User.builder()
				        .fullname("Eréndira Curicaveri")
				        .username("erendira")
				        .email("erendira.curicaveri@gmail.com")
				        .expiryDateCredentials(LocalDate.now().plusDays(1000))
				        .expiryDateAccount(LocalDate.now().plusDays(1000))
				        .lockedAccount(false)
				        .password("$2a$12$ZIOUB3aIzuXdQ7RA/DJZr.bM6TcxOO7QuA7/1WADM7n45d7nVXTpO") //fjgklj
				        .enabled(true)
				        .roleSet(Set.of(roleService.findById(1L).get(),
				                roleService.findById(2L).get()))
				        .build());
				
				repo.save(User.builder()
				        .fullname("Ireta Tariácuri")
				        .username("ireta")
				        .email("ireta.tariacuri@gmail.com")
				        .password("$2a$12$.tU3XGBephwMJo9O/qYiSOh5.vzuCF12G9PjS.T3wX/.AcmXycZaq") //fjakf
				        .expiryDateCredentials(LocalDate.now().plusDays(1000))
				        .expiryDateAccount(LocalDate.now().plusDays(1000))
				        .lockedAccount(false)
				        .enabled(true)
				        .roleSet(Set.of(roleService.findById(1L).get(),
				                roleService.findById(2L).get()))
				        .build());
				 
				repo.save(User.builder()
				        .fullname("Hirepan Huiramangari")
				        .username("hirepan")
				        .password("$2a$12$2nKshrC97xGO0KqcN2dag.AsdWnpVsARqok8MPV0DLAy9EOE9ttPe")//$2a$12$.
				        .email("hirepan.huiramangari@gmail.com")
				        .expiryDateCredentials(LocalDate.now().plusDays(1000))
				        .expiryDateAccount(LocalDate.now().plusDays(1000))
				        .lockedAccount(false)
				        .enabled(true)
				        .roleSet(Set.of(roleService.findById(1L).get(),
				                roleService.findById(3L).get()))
				        .build());
				
				repo.save(User.builder()
				        .fullname("Xaratanga Curicaveri")
				        .username("xaratanga")
				        .email("xaratanga.curicaveri@gmail.com")
				        .password("$2a$12$2nKshrC97xGO0KqcN2dag.AsdWnpVsARqok8MPV0DLAy9EOE9ttPe")//$2a$12$. Lo que el usuario pone de password nunca se guarda en ningún lado, solo se guarda la encriptación
				        .expiryDateCredentials(LocalDate.now().plusDays(1000))
				        .expiryDateAccount(LocalDate.now().plusDays(1000))
				        .lockedAccount(false)
				        .enabled(true)
				        .roleSet(Set.of(roleService.findById(1L).get(),
				                roleService.findById(4L).get()))
				        .build());
		
	}
	
	
	@Override
	public void saveDataTestProd() {
		repo.save(new User("curso", 
				"$2a$12$ZIOUB3aIzuXdQ7RA/DJZr.bM6TcxOO7QuA7/1WADM7n45d7nVXTpO", //fjgklj 
				"curso@gmail.com", 
				"Usuario del curso",
				LocalDate.now().plusDays(2),
				false,
				LocalDate.now().plusDays(5),
				true,
				Set.of(roleService.findByRolename("ADMIN").get()
				)));

	}

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	@Override
	public void delete(User user) {
		repo.delete(user);	
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public Boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<User> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = this.findByUsername(username);
		if (userOpt.isEmpty()) {
			log.warn("Usuario no identificado: " + username);
			throw new UsernameNotFoundException(username);
		}
		log.info("Usuario autenticado: " + username + " -> " + userOpt.get().getFullname());
		return userOpt.get();
	}

	@Override
	public void deleteByUsername(String username) {
		repo.deleteByUsername(username);
		
	}

	@Override
	public Boolean existsByUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findOptByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

}
