package org.core.curso.testspring.controller;

import org.core.curso.testspring.service.IServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class StartControllerImpl {

	@Autowired
	private IServiceCustomer customerService;

	@GetMapping({ "/contactsGet" })
	public String contactsGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA contactsGet");
		// Inject data into html page
		//this.injectCommonAttributesInHtmlPage(principal, model, request);
		return "contacts";
	}

	
	/*
	@Override
	@GetMapping({ "/loginGet" })
	public String loginGet(Model model) {
		System.out.println("TRAZA loginGet");
		// Inject data into html page
		model.addAttribute("login", loginService.newEntity());
		//
		return "loginPage";
	}

	@Override
	@PostMapping({ "/loginPost" })
	public String loginPost(@Valid Login login, BindingResult bindingResult, HttpServletRequest request, Model model) {
		System.out.println("TRAZA loginPost");
		if (bindingResult.hasErrors()) {
			log.warn("VALIDATION ERRORS!!!: " + bindingResult.getAllErrors());
			model.addAttribute("errorMessage", "Validation failed. Please check your input.");
			return "loginPage";
		}
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					login.getUsername(), login.getPassword());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			if (authentication.isAuthenticated()) {
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);

				HttpSession session = request.getSession(true);
				session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
				return "redirect:/homeGet";
			} else {
				throw new BusinessException("Invalid credentials. Please try again.");
			}
		} catch (Exception ex) {
			log.error("LOGIN FAILED: ", ex);
			model.addAttribute("errorMessage", ex.getMessage());
			return "loginPage";
		}

	}

	@Override
	@GetMapping({ "/dumpDBGet" })
	public String dumpDBGet() {
		System.out.println("TRAZA dumpDBGet");
		sqlCreatorServiceImpl.dumpDB();
		return "masterFull";
	}

	@Override
	@GetMapping({ "/logoutGet" })
	public String logoutGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA logoutGet");
		// Invalidamos la session
		request.getSession().invalidate();
		//
		// Inject data into html page
		model.addAttribute("login", loginService.newEntity());
		//
		// return "redirect:/loginGet?logoutOk";
		return "loginPage";
	}

	@Override
	@GetMapping({ "/homeGet" })
	public String homeGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA homeGet");
		// Inject data into html page
		this.injectCommonAttributesInHtmlPage(principal, model, request);
		List<ProductFamily> productFamilies = productFamilyService.findAll();
		model.addAttribute("productFamilies", productFamilies);
		return "masterFull";
	}


	@Override
	@GetMapping({ "/featuresGet" })
	public String featuresGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA featuresGet");
		// Inject data into html page
		this.injectCommonAttributesInHtmlPage(principal, model, request);
		return "features";
	}

	@Override
	@GetMapping({ "/termsGet" })
	public String termsGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA termsGet");
		// Inject data into html page
		this.injectCommonAttributesInHtmlPage(principal, model, request);
		return "terms";
	}

	@Override
	@GetMapping({ "/aboutGet" })
	public String aboutGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA aboutGet");
		// Inject data into html page
		this.injectCommonAttributesInHtmlPage(principal, model, request);
		return "about";
	}

	@Override
	public void injectCommonAttributesInHtmlPage(Principal principal, Model model, HttpServletRequest request) {

		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("userPicture", "");

		model.addAttribute("languageTagStringList",
				languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		model.addAttribute("requestURI", request.getRequestURI());

		log.warn("languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		log.warn("requestURI", request.getRequestURI());
	}
	 */
}