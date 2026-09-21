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
public class OrderController {
	
	private final ValuationService valuationService_1;
	private final IProductService service;
	private final IValuationService valuationService;
	private final IFamilyService familyService;
	
	public OrderController(
			IProductService service, 
			IValuationService valuationService, 
			IFamilyService familyService, ValuationService valuationService_1) {
		super();
		this.service = service;
		this.valuationService = valuationService;
		this.familyService = familyService;
		this.valuationService_1 = valuationService_1;
	}
	
	@GetMapping({ "/order" })
	public String order(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA order");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		return "cart/orderLineAddToCart";
	}
	
	@GetMapping({ "/shoppingCart/cartSummary" })
	public String cartSummary(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA cartSummary");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		// TODO inyectar pedido (order) y líneas de pedido (orderLine)
		return "cart/cartSummary";
	}
	
	@GetMapping({ "/shoppingCart/cartCheckout" })
	public String cartCheckout(
			Principal principal,
			Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA cartCheckout");
		model.addAttribute("username", principal.getName());
		model.addAttribute("itemList", service.findAll());
		// TODO inyectar pedido (order) y líneas de pedido (orderLine)
		// TODO o valorar pasar solo los totales
		return "cart/cartPayCard";
	}
	

}