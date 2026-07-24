package org.core.curso.testspring.controller;

import org.core.curso.testspring.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import java.security.Principal;

@Controller
@Log
public class CustomerController {
	
	private final ICustomerService customerService;
	
	public CustomerController(ICustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping({ "/customerViewGet/{id}" })
	public String customerViewGet(
			@PathVariable Long id,
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerViewGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("customer", customerService.findById(id).get());
		return "customer/customerView";
	}
	
	@GetMapping({ "/customerList" })
	public String customerList(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerList");
		model.addAttribute("username", principal.getName());
		model.addAttribute("customerList", customerService.findAll());
		return "customer/customerList";
	}

}