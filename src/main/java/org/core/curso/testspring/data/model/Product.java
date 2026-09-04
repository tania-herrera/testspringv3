package org.core.curso.testspring.data.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
    @Size(min=1, max=50, message="Campo 'name' debe tener entre 1 y 50 caracteres")
	private String name;
    
	@NotNull
	@Size(min=1, max=250, message="Campo 'URL' debe tener entre 1 y 250 caracteres")
	private String URL;
	
	@NotNull
	@DecimalMin(value = "0.00", message = "Campo 'price' valor mínimo 0.00")
	@DecimalMax(value = "999.99", message = "Campo 'price' valor máximo 999.99")
	private Double price;

	@NotNull
	@Min(value = 0, message="Campo 'stock' valor mínimo 0")
	private Integer stock;

	@NotNull
	@DecimalMin(value = "0.00", message = "Campo 'discount' valor mínimo 0.00")
	@DecimalMax(value = "100.00", message = "Campo 'discount' valor máximo 100.00")
	private Double discount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "valuationId", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private Valuation valuation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "familyId", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private Family family;
	
}
