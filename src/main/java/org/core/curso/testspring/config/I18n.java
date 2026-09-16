package org.core.curso.testspring.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class I18n {
	
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver("language");
        //resolver.setDefaultLocale(Locale.US);
        resolver.setDefaultLocale(Locale.of("es", "ES"));
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        //http://localhost:8080/testspring/loginGet?lang=es
        return interceptor;
    }

    @Bean
    public WebMvcConfigurer localeConfigurer(
            LocaleChangeInterceptor localeChangeInterceptor) {

    	// Lo siguiente crea una clase anónima llamada WebMvcConfigurer,
    	// luego crea una instancia sin darle nombre (anónima) e incluye
    	// los métodos que necesite (en este caso solo 1):
        return new WebMvcConfigurer() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(localeChangeInterceptor);
            }
        };
    }

}


/*

public class WebMvcConfig() implements WebMvcConfigurer{

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(localeChangeInterceptor);
            }
}

...
new WebMvcConfig()
...



 */

