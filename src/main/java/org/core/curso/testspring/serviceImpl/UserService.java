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
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final LoginFailureHandler loginFailureHandler;
	
	private final IUserRepository repo;
	
	@Autowired
	private IRoleService roleService = new RoleService();

	UserService(IUserRepository repo, LoginFailureHandler loginFailureHandler) {
		this.repo = repo;
		this.loginFailureHandler = loginFailureHandler;
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
	public void saveDataTest() {
		repo.save(new User("jag", 
				"$2a$12$.uHv/TRKP9uA.rpzUERB4unKcKM/AmLh5UZW4p1P6M5/Sxb4UZuXq", 
				"jag@gmail.com", 
				"José A. Gutiérrez",
				LocalDate.now().plusDays(-2),
				false,
				LocalDate.now().plusDays(-5),
				false,
				Set.of(roleService.findById("ADMIN").get(),
						roleService.findById("CUSTOMER").get())
				));
		
		repo.save(User.builder()
				.username("ana")
				.email("ana@gmail.com")
				.fullname("Ana Sanz")
				.expiryDateAccount(LocalDate.now().plusDays(2))
				.expiryDateCredentials(LocalDate.now().plusDays(5))
				.lockedAccount(false)
				.enabled(true)
				.password("$2a$12$fINwy2MFUfMzlSIqi3eIXeoh2qKrOf.JIn8/Qq7ontp.Mtia8jSI2")
				.roleSet(Set.of(roleService.findById("MANAGER").get(),
								roleService.findById("CUSTOMER").get()))
				.build()
				);
		repo.save(User.builder()
				.username("luis")
				.email("luis@gmail.com")
				.fullname("Luis Pérez")
				.expiryDateAccount(LocalDate.now().plusDays(2))
				.expiryDateCredentials(LocalDate.now().plusDays(5))
				.lockedAccount(false)
				.enabled(true)
				.password("$2a$12$pOtzLXBuqdINw7wM3spdc.4P8VULWYyc5qIFn99ozxoRJbL3H.oYG")
				.roleSet(Set.of(roleService.findById("USER").get(),
						roleService.findById("ADMIN").get()))
				.build()
				);
		
	}

	@Override
	public void save(User user) {
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
		System.out.println("TRAZA 1: " + username);
		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		System.out.println("TRAZA 2: " + userOpt.get().getFullname());
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
