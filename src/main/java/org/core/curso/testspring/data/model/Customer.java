package org.core.curso.testspring.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	@Id private Long id;
	private String name;
	private Integer age;
	private Double totalPurchases;
	private String cardNumber;
	
	public Customer(String name, Integer age, Double totalPurchases, String cardNumber) {
		super();
		this.name = name;
		this.age = age;
		this.totalPurchases = totalPurchases;
		this.cardNumber = cardNumber;
	}
	
}
