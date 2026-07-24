package org.core.curso.testspring.config;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//
		System.out.println("TRAZA: entrando en LoginFailure Handler");
		System.out.println("TRAZA: excepción: " + exception.getClass().getName());
		//super.onAuthenticationFailure(request, response, exception);
		String url = "/loginGet?error=unknownError";
		
		if (exception instanceof BadCredentialsException) {
			url = "/loginGet?error=badCredentials";
		}
		else {
			if (exception instanceof LockedException) {
				url = "/loginGet?error=lockedAccount";
			}
			else {
				if (exception instanceof DisabledException) {
					url = "/loginGet?error=disabledAccount";
				}
				else {
					if (exception instanceof CredentialsExpiredException) {
						url = "/loginGet?error=credentialsExpired";
					}
					else {
						if (exception instanceof AccountExpiredException) {
							url = "/loginGet?error=accountExpired";
						}
					}
				}
			}
		}
		getRedirectStrategy().sendRedirect(request, response, url);
	}
	
}
