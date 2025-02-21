package com.etp.cakeshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Renuka Configuration class to enable Cross-Origin Resource Sharing
 *         (CORS) for the Cake Shop application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Configures CORS settings to allow frontend requests from Angular
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
}
