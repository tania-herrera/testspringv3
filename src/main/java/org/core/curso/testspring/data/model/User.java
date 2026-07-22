package org.core.curso.testspring.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.java.Log;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Log
@Entity
@Table(name = "USERS")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
		
	@NotNull
	@Column(name = "USERNAME", unique = true, nullable = false)
	@Size(min = 3, max = 50, message = "Username must have 3 to 50 characters")
	private String username;
	
	@ToString.Exclude
	@NotNull
	@Column(name = "PASSWORD", nullable = false)
	@Size(max = 100)
	private String password;
	
	@Email
	@Column(name = "EMAIL", unique = true, nullable = false)
	@Size(min = 3, max = 100, message = "Email must have 3 to 100 characters")
	private String email;
	
	@NotNull
	@Column(nullable = false)
	@Size(max = 100, message = "Fullname must have up to 100 characters")
	private String fullname;
	
	@NotNull
	@Column(name = "EXPIRY_DATE_ACCOUNT", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDateAccount;
	
	@NotNull
	@Column(name = "LOCKED_ACCOUNT", nullable = false)
	private Boolean lockedAccount;
	
	@NotNull
	@Column(name = "EXPIRY_DATE_CREDENTIALS", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDateCredentials;
	
	@NotNull
	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "USERS_HAS_ROLES",
		joinColumns = @JoinColumn(name = "FK_USERS_ID", referencedColumnName = "ID"),
		inverseJoinColumns = @JoinColumn(name = "FK_ROLES_ROLENAME", referencedColumnName = "ROLENAME")
		)
	private Set<Role> roleSet = new HashSet<Role>();

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*
		// Versión con lista intermedia:
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
		// Obtener los roles del usuario autenticado:
		this.getRoleSet().stream()
			.map(x -> x.getRolename())
			.forEach(x -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(x)));
		// Mostrar los roles del usuario autenticado:
		log.info("Roles de: " + this.getUsername() + ", " + this.getFullname() + ": " +
			simpleGrantedAuthorityList.stream()
				.map(x -> x.getAuthority())
				.collect(Collectors.joining("|", "{", "}")));
		//		
		return simpleGrantedAuthorityList;
		*/
		//
		// Versión sin lista intermedia y simplificada:
		return this.getRoleSet().stream()
			.map(Role::getRolename)
			.peek(r -> log.info("\t|" + r + "|"))
			.map(SimpleGrantedAuthority::new)
			.toList();
	}
	
	@Override
	public boolean isAccountNonExpired() {	
		return this.expiryDateAccount.isAfter(LocalDate.now());
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.getLockedAccount();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.expiryDateCredentials.isAfter(LocalDate.now());
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public User(
			@NotNull @Size(min = 3, max = 50, message = "Username must have 3 to 50 characters") String username,
			@NotNull @Size(max = 100) String password,
			@Email @Size(min = 3, max = 100, message = "Email must have 3 to 100 characters") String email,
			@NotNull @Size(max = 100, message = "Fullname must have up to 100 characters") String fullname,
			@NotNull LocalDate expiryDateAccount, 
			@NotNull Boolean lockedAccount,
			@NotNull LocalDate expiryDateCredentials, 
			@NotNull Boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.expiryDateAccount = expiryDateAccount;
		this.lockedAccount = lockedAccount;
		this.expiryDateCredentials = expiryDateCredentials;
		this.enabled = enabled;
		this.roleSet = new HashSet<Role>();
	}
	
	public User(
			@NotNull @Size(min = 3, max = 50, message = "Username must have 3 to 50 characters") String username,
			@NotNull @Size(max = 100) String password,
			@Email @Size(min = 3, max = 100, message = "Email must have 3 to 100 characters") String email,
			@NotNull @Size(max = 100, message = "Fullname must have up to 100 characters") String fullname,
			@NotNull LocalDate expiryDateAccount, 
			@NotNull Boolean lockedAccount,
			@NotNull LocalDate expiryDateCredentials, 
			@NotNull Boolean enabled,
			@NotNull Set<Role> roleSet) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.expiryDateAccount = expiryDateAccount;
		this.lockedAccount = lockedAccount;
		this.expiryDateCredentials = expiryDateCredentials;
		this.enabled = enabled;
		this.roleSet = roleSet;
	}

	@Override
	public int hashCode() {
		return Hibernate.getClass(this).hashCode(); // Hibernate recommendation
		//return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false; // Hibernate recommendation
		User other = (User) obj;
		return getId() != null
				&& Objects.equals(getId(), other.getId()); // Hibernate recommendation
		//return Objects.equals(id, other.id);
	}
	
	
	
}
