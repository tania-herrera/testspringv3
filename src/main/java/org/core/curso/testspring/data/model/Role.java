package org.core.curso.testspring.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "ROLENAME", unique = true, nullable = false)
	@Size(min = 3, max = 50, message = "Rolename must have 2 to 50 characters")
	private String rolename;
	
	@ToString.Exclude
	@ManyToMany(mappedBy = "roleSet")
	private Set<User> userSet = new HashSet<User>();
	
	public Role(String rolename) {
		this.rolename = rolename;
		this.userSet = new HashSet<User>();
	}

	@Override
	public int hashCode() {
		return Hibernate.getClass(this).hashCode(); // Hibernate recommendation
		//return Objects.hash(rolename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false; // Hibernate recommendation
		Role other = (Role) obj;
		return getRolename() != null
			&& Objects.equals(getRolename(), other.getRolename()); // Hibernate recommendation
		//return Objects.equals(rolename, other.rolename);
	}

	
	
}
