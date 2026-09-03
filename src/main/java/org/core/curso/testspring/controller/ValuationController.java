package org.core.curso.testspring.controller;

import org.core.curso.testspring.data.model.Valuation;
import org.core.curso.testspring.service.IValuationService;
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
public class ValuationController {
	
	private final IValuationService service;
	
	public ValuationController(IValuationService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/valuationDeleteGet/{id}")
	public String valuationDeleteGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en valuationDeleteGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "valuation/valuationDelete";
	}
	
	@GetMapping("/valuationDeletePost/{id}")
	public String valuationDeleteConfirmed(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en valuationDeletePost");
		service.deleteById(id);
		return "redirect:/valuationList"; 
	}
	
	@GetMapping("/valuationAddGet")
	public String valuationAddGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en valuationAddGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", new Valuation());
		return "valuation/valuationAdd";
	}
	
	@PostMapping("/valuationAddPost")
	public String valuationAddPost(
			@Valid Valuation valuation, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en valuationAddPost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "valuation/valuationAdd";
		} else {
			// Debemos guardar el nuevo registro
			service.save(valuation);
			// Queremos ir de nuevo a la lista
			return "redirect:/valuationList"; 
		}
	}
	
	@GetMapping("/valuationUpdateGet/{id}")
	public String valuationUpdateGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en valuationUpdateGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "valuation/valuationUpdate";
	}
	
	@PostMapping("/valuationUpdatePost")
	public String valuationUpdatePost(
			@Valid Valuation valuation, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en valuationUpdatePost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "redirect:/valuationUpdateGet/" + valuation.getId();
		} else {
			// Debemos guardar lo modificado
			service.save(valuation);
			// Queremos ir de nuevo a la lista
			return "redirect:/valuationList"; 
		}
	}

	@GetMapping({ "/valuationList" })
	public String customerList(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA valuationList");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		return "valuation/valuationList";
	}

}