package org.core.curso.testspring.controller;

import org.core.curso.testspring.data.model.Family;
import org.core.curso.testspring.service.IFamilyService;
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
public class FamilyController {
	
	private final IFamilyService service;
	
	public FamilyController(IFamilyService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/familyDeleteGet/{id}")
	public String familyDeleteGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en familyDeleteGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "family/familyDelete";
	}
	
	@GetMapping("/familyDeletePost/{id}")
	public String familyDeleteConfirmed(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en familyDeletePost");
		service.deleteById(id);
		return "redirect:/familyList"; 
	}
	
	@GetMapping("/familyAddGet")
	public String familyAddGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en familyAddGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", new Family());
		return "family/familyAdd";
	}
	
	@PostMapping("/familyAddPost")
	public String familyAddPost(
			@Valid Family family, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en familyAddPost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "family/familyAdd";
		} else {
			// Debemos guardar el nuevo registro
			service.save(family);
			// Queremos ir de nuevo a la lista
			return "redirect:/familyList"; 
		}
	}
	
	@GetMapping("/familyUpdateGet/{id}")
	public String familyUpdateGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en familyUpdateGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "family/familyUpdate";
	}
	
	@PostMapping("/familyUpdatePost")
	public String familyUpdatePost(
			@Valid Family family, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en familyUpdatePost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "redirect:/familyUpdateGet/" + family.getId();
		} else {
			// Debemos guardar lo modificado
			service.save(family);
			// Queremos ir de nuevo a la lista
			return "redirect:/familyList"; 
		}
	}

	@GetMapping({ "/familyList" })
	public String customerList(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA familyList");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		return "family/familyList";
	}

}