package org.core.curso.testspring.config;

import org.core.curso.testspring.service.ICustomerService;
import org.core.curso.testspring.service.IFamilyService;
import org.core.curso.testspring.service.IProductService;
import org.core.curso.testspring.service.IRoleService;
import org.core.curso.testspring.service.IUserService;
import org.core.curso.testspring.service.IValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
//@Profile("dev")
public class DataInitializer {
	
	private final IRoleService roleService;
	private final IUserService userService;
	private final ICustomerService customerService;
	private final IValuationService valuationService;
	private final IFamilyService familyService;
	private final IProductService productService;
	
	
	public DataInitializer(
			IRoleService roleService, 
			IUserService userService, 
			ICustomerService customerService,
			IValuationService valuationService,
			IFamilyService familyService,
			IProductService productService) {
		super();
		this.roleService = roleService;
		this.userService = userService;
		this.customerService = customerService;
		this.valuationService = valuationService;
		this.familyService = familyService;
		this.productService = productService;
	}

	public void initialize() {
		log.info("Initializing H2 Database...");

		roleService.saveDataTest();
		userService.saveDataTest();
		customerService.saveDataTest();
		valuationService.saveDataTest();
		familyService.saveDataTest();
		productService.saveDataTest();
		
		log.info("Initialization finished.");
	}
	
}
