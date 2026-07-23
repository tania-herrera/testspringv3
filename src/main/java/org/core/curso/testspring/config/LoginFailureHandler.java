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
		//super.onAuthenticationFailure(request, response, exception);
		String url = "/loginGet?error";
		
		if (exception instanceof BadCredentialsException) {
			url = "/loginGet?badCredentials";
		}
		else {
			if (exception instanceof LockedException) {
				url = "/loginGet?lockedAccount";
			}
			else {
				if (exception instanceof DisabledException) {
					url = "/loginGet?disabledAccount";
				}
				else {
					if (exception instanceof CredentialsExpiredException) {
						url = "/loginGet?credentialExpired";
					}
					else {
						if (exception instanceof AccountExpiredException) {
							url = "/loginGet?accountExpired";
						}
					}
				}
			}
			getRedirectStrategy().sendRedirect(request, response, url);
		}
	}
	
}
