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
@Profile("prod")
public class ProdDataInitializer implements IDataInitializer{
	
	private final IRoleService roleService;
	private final IUserService userService;
	private final IValuationService valuationService;
	private final IFamilyService familyService;
	
	
	public ProdDataInitializer(
			IRoleService roleService, 
			IUserService userService, 
			IValuationService valuationService,
			IFamilyService familyService) {
		super();
		this.roleService = roleService;
		this.userService = userService;
		this.valuationService = valuationService;
		this.familyService = familyService;
	}

	public void initialize() {
		log.info("Initializing H2 Database...");

		roleService.saveDataTestProd();
		userService.saveDataTestProd();
		valuationService.saveDataTest();
		familyService.saveDataTest();
		
		log.info("Initialization finished.");
	}
	
}
