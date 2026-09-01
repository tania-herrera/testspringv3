package org.core.curso.testspring.controller;

import org.core.curso.testspring.data.model.Customer;
import org.core.curso.testspring.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Controller
@Slf4j
public class CustomerController {
	
	private final ICustomerService customerService;
	
	public CustomerController(ICustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping("/customerDeleteGet/{id}")
	public String customerDeleteGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en customerDeleteGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("customer", customerService.findById(id).get());
		return "customer/customerDelete";
	}
	
	@GetMapping("/customerDeletePost/{id}")
	public String productDeleteConfirmed(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en customerDeletePost");
		customerService.deleteById(id);
		return "redirect:/customerList"; 
	}

	
	@GetMapping("/customerAddGet")
	public String customerAddGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en customerAddGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("customer", new Customer());
		return "customer/customerAdd";
	}
	
	@PostMapping("/customerAddPost")
	public String customerAddPost(
			@Valid Customer customer, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en customerAddPost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "customer/customerAdd";
		} else {
			// Debemos guardar el nuevo registro
			customerService.save(customer);
			// Queremos ir de nuevo a la lista
			return "redirect:/customerList"; // "customerList";
		}
	}
	
	@GetMapping("/customerUpdateGet/{id}")
	public String customerUpdateGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en customerUpdateGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("customer", customerService.findById(id).get());
		return "customer/customerUpdate";
	}
	
	@PostMapping("/customerUpdatePost")
	public String customerUpdatePost(
			@Valid Customer customer, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en customerUpdatePost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "redirect:/customerUpdateGet/" + customer.getId();
		} else {
			// Debemos guardar lo modificado
			customerService.save(customer);
			// Queremos ir de nuevo a la lista
			return "redirect:/customerList"; // "customerList";
		}
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