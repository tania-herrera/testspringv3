package org.core.curso.testspring.controller;

import org.core.curso.testspring.data.model.User;
import org.core.curso.testspring.service.IRoleService;
import org.core.curso.testspring.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
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
public class UserController {
	
	private final AuthenticationManager authenticationManager;
	private final IUserService service;
	private final IRoleService roleservice;
	
	public UserController(IUserService service, IRoleService roleservice, AuthenticationManager authenticationManager) {
		super();
		this.service = service;
		this.roleservice = roleservice;
		this.authenticationManager = authenticationManager;
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/userDeleteGet/{id}")
	public String userDeleteGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en userDeleteGet");
		model.addAttribute("title", "web.userList.name.userDelete");
		model.addAttribute("username", principal.getName());
		model.addAttribute("user", service.findById(id).get());
		return "user/userViewDelete";
	}
	
	@GetMapping("/userDeletePost/{id}")
	public String userDeleteConfirmed(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en userDeletePost");
		service.deleteById(id);
		return "redirect:/userList"; 
	}

	
	@GetMapping("/userAddGet")
	public String userAddGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en userAddGet");
		model.addAttribute("title","web.html.userAdd.title");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", new User());
		model.addAttribute("roleSet", this.roleservice.findAll());
		return "user/userAddUpdate";
	}
	
	@PostMapping("/userAddPost")
	public String userAddPost(
			@Valid @ModelAttribute ("item") User user, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en userAddPost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			model.addAttribute("title", "web.html.userAdd.title");
			model.addAttribute("username", principal.getName());
			model.addAttribute("item", user);
			model.addAttribute("roleSet", this.roleservice.findAll());
			return "user/userList"; 
			
		} else {
			
			// Debemos guardar el nuevo registro
			service.save(user);
			// Queremos ir de nuevo a la lista
			model.addAttribute("message", "User saved");
			model.addAttribute("username", principal.getName());
			model.addAttribute("itemList", service.findAll());
			return "user/userList"; }
			//return "redirect:/userList"; 
		}
	
	@GetMapping("/userUpdateGet/{id}")
	public String userUpdateGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en userUpdateGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("user", service.findById(id).get());
		return "user/userUpdate";
	}
	
	@PostMapping("/userUpdatePost")
	public String userUpdatePost(
			@Valid User user, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en userUpdatePost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "redirect:/userUpdateGet/" + user.getId();
		} else {
			// Debemos guardar lo modificado
			model.addAttribute("title", "web.html.userAdd.title");
			model.addAttribute("username", principal.getName());
			model.addAttribute("item", user);
			model.addAttribute("roleSet", this.roleservice.findAll());
			return "redirect:/userList"; // "userList";
		}
	}

	
	@GetMapping({ "/userViewGet/{id}" })
	public String userViewGet(
			@PathVariable Long id,
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA userViewGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("user", service.findById(id).get());
		return "user/userViewDelete";
	}
	
	@GetMapping({ "/userList" })
	public String userList(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA userList");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		return "user/userList";
	}

}