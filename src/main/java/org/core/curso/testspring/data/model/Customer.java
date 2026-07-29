package org.core.curso.testspring.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
public class Customer {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
    @Size(min=1, max=50, message="Campo 'name' debe tener entre 1 y 50 caracteres")
	private String name;
    
	@NotNull
	private Integer age;
	
	@NotNull
	@DecimalMin("0.00")
	@DecimalMax("999999.99")
	private Double totalPurchases;
	
	@Size(min=0, max=16, message="Campo 'Card number' debe tener entre 0 y 16 caracteres")
	private String cardNumber;
	
	public Customer(String name, Integer age, Double totalPurchases, String cardNumber) {
		super();
		this.name = name;
		this.age = age;
		this.totalPurchases = totalPurchases;
		this.cardNumber = cardNumber;
	}
	
}
