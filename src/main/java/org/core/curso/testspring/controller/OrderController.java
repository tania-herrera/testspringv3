package org.core.curso.testspring.controller;

import org.core.curso.testspring.service.IFamilyService;
import org.core.curso.testspring.service.IProductService;
import org.core.curso.testspring.service.IValuationService;
import org.core.curso.testspring.serviceImpl.ValuationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
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
		return "carrito/orderLineAddToCart";
	}

}