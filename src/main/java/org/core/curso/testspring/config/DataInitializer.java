package org.core.curso.testspring.config;

import org.core.curso.testspring.service.ICustomerService;
import org.core.curso.testspring.service.IRoleService;
import org.core.curso.testspring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
//@Profile("dev")
public class DataInitializer {
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private ICustomerService customerService;

	public void initialize() {
		log.info("Initializing H2 Database...");

		roleService.saveDataTest();
		userService.saveDataTest();
//		customerService.saveDataTest();
		
		log.info("Initialization finished.");
	}
	
}
