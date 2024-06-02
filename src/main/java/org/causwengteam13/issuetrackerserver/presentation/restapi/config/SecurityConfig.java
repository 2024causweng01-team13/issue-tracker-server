package org.causwengteam13.issuetrackerserver.presentation.restapi.config;

import java.util.List;

import org.causwengteam13.issuetrackerserver.presentation.restapi.auth.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAthenticationFilter;

	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedMethods(List.of("*"));
		config.setAllowedOriginPatterns(List.of("*"));
		config.setAllowCredentials(true);
		config.setExposedHeaders(List.of("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(AbstractHttpConfigurer::disable)
			.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize ->
				authorize.requestMatchers("/**").permitAll()
			)
			.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
				response.setContentType("application.json");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("{\"message\": access denied}");
			}));

		httpSecurity.addFilterBefore(jwtAthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}
