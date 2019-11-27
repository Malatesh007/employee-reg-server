package com.example.register.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig {
	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	 @Bean
//	    CorsConfigurationSource corsConfigurationSource()
//	    {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.applyPermitDefaultValues();
//	        configuration.setAllowedOrigins( Collections.singletonList( "*" ) );
//	        configuration.setAllowedMethods( getAllHttpMethods() );
//	        configuration.setAllowCredentials( true );
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration( "/**", configuration );
//	        return source;
//	    }

//	@Bean
//	public CorsFilter corsFilter() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//	    // Allow anyone and anything access. Probably ok for Swagger spec
//	    CorsConfigurat	ion config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("*");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("*");
//
//	    source.registerCorsConfiguration("/v2/api-docs", config);
//	    return new CorsFilter(source);
//	}
}
