package org.core.curso.testspring.data.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Family {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo 'name' no puede estar vacío")
    @Size(min=1, max=50, message="Campo 'name' debe tener entre 1 y 50 caracteres")
	@Column(nullable=false, columnDefinition="CHAR(50) CHECK(LENGTH(NAME) >= 1)")
	private String name;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "family")
	private List<Product> productList = new ArrayList<Product>();
	
	public Family(String name) {
		super();
		this.name = name;
	}
	
}
