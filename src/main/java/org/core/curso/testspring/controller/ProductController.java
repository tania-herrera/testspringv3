package org.core.curso.testspring.controller;

import org.core.curso.testspring.data.model.Product;
import org.core.curso.testspring.service.IFamilyService;
import org.core.curso.testspring.service.IProductService;
import org.core.curso.testspring.service.IValuationService;
import org.core.curso.testspring.serviceImpl.ValuationService;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
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
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Controller
@Getter
@Slf4j
public class ProductController {
	
	private final ValuationService valuationService_1;
	private final IProductService service;
	private final IValuationService valuationService;
	private final IFamilyService familyService;
	
	public ProductController(
			IProductService service, 
			IValuationService valuationService, 
			IFamilyService familyService, ValuationService valuationService_1) {
		super();
		this.service = service;
		this.valuationService = valuationService;
		this.familyService = familyService;
		this.valuationService_1 = valuationService_1;
	}
	
	// TODO pendiente ver si hay que limitar el borrado de productos
	@GetMapping("/productDeleteGet/{id}")
	public String productDeleteGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en productDeleteGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "product/productDelete";
	}
	
	@GetMapping("/productDeletePost/{id}")
	public String productDeleteConfirmed(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en productDeletePost");
		service.deleteById(id);
		return "redirect:/productList"; 
	}
	
	@GetMapping("/productAddGet")
	public String productAddGet(
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en productAddGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", new Product());
		model.addAttribute("valuationList", this.getValuationService().findAll());
		model.addAttribute("familyList", this.getFamilyService().findAll());
		return "product/productAdd";
	}
	
	@PostMapping("/productAddPost")
	public String productAddPost(
			@Valid @ModelAttribute("item") Product product, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en productAddPost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			model.addAttribute("username", principal.getName());
			model.addAttribute("item", product);
			model.addAttribute("valuationList", this.getValuationService().findAll());
			model.addAttribute("familyList", this.getFamilyService().findAll());			return "product/productAdd";
		} else {
			// Debemos guardar el nuevo registro
			service.save(product);
			// Queremos ir de nuevo a la lista
			return "redirect:/productList"; 
		}
	}
	
	@GetMapping("/productUpdateGet/{id}")
	public String productUpdateGet(
			@PathVariable Long id, 
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en productUpdateGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "product/productUpdate";
	}
	
	@PostMapping("/productUpdatePost")
	public String productUpdatePost(
			@Valid Product product, 
			BindingResult bindingResult,
			Principal principal, 
			Model model,
			HttpServletRequest request) {
		log.info("TRAZA: entrando en productUpdatePost");
		if (bindingResult.hasErrors()) {
			log.error("Formulario con errores: " + bindingResult.getAllErrors());
			return "redirect:/productUpdateGet/" + product.getId();
		} else {
			// Debemos guardar lo modificado
			service.save(product);
			// Queremos ir de nuevo a la lista
			return "redirect:/productList"; 
		}
	}

	@GetMapping({ "/productViewGet/{id}" })
	public String productViewGet(
			@PathVariable Long id,
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA productViewGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("item", service.findById(id).get());
		return "product/productView";
	}
	
	@GetMapping({ "/productList" })
	public String customerList(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA productList");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		return "product/productList";
	}

}