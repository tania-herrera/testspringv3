package org.core.curso.testspring.controller;

import org.core.curso.testspring.data.model.Role;
import org.core.curso.testspring.service.IRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Controller
@Slf4j
public class RoleController {
	
	private final IRoleService service;
	
	public RoleController(IRoleService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/roleDeleteGet/{id}")
	public String roleDeleteGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en roleDeleteGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "role/roleDelete";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/roleDeletePost/{id}")
	public String roleDeleteConfirmed(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en roleDeletePost");
		service.deleteById(id);
		return "redirect:/roleList"; 
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/roleAddGet")
	public String roleAddGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en roleAddGet");
		
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", new Role());
		return "role/roleAdd";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/roleAddPost")
	public String roleAddPost(
			@Valid @ModelAttribute("item") Role role, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en roleAddPost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			model.addAttribute("username", principal.getName());
			model.addAttribute("item", role);
			return "role/roleAdd";
		} else {
			// Debemos guardar el nuevo registro
			model.addAttribute("message", service.save(role));
			model.addAttribute("username", principal.getName());
			model.addAttribute("itemList", service.findAll());
			// Queremos ir de nuevo a la lista
			return "redirect:/roleList"; 
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/roleUpdateGet/{rolename}")
	public String roleUpdateGet(
			@PathVariable String rolename, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en roleUpdateGet");
		model.addAttribute("item", service.findByRolename(rolename).get());
		return "role/roleUpdate";
	}
	

	
	
	@PostMapping("/roleUpdatePost")
	public String roleUpdatePost(
			@Valid Role role, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en roleUpdatePost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "redirect:/roleUpdateGet/" + role.getRolename();
		} else {
			// Debemos guardar lo modificado
			model.addAttribute("username", principal.getName());
			model.addAttribute("itemList", service.findAll());
			// Queremos ir de nuevo a la lista
			return "redirect:/roleList"; 
		}
		
		
	}

	@GetMapping({ "/roleList" })
	public String roleList(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA roleList");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		return "role/roleList";
	}

}