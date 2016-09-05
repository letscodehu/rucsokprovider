package com.rucsok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.*;
import static com.google.common.base.Predicates.*;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rucsok" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.rucsok.test.*"))
public class RucsokResourceServerApp {

	public static void main(String[] args) {
		SpringApplication.run(RucsokResourceServerApp.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**/*").allowedOrigins("http://10.0.14.85:8100", "EPHUBUDW0489:8100");
            }
        };
    }
	
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        	.host("http://localhost:8080")
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Rucsokprovider API")
            .description("How about slacking around with Rucsokprovider API?")
            .version("1.0.0")
            .contact(new Contact("Papp Krisztián", "https://www.letscode.hu", "fejlesztes@letscode.hu"))
            .termsOfServiceUrl("http://terms-of-services.url")
            .license("LICENSE")
            .licenseUrl("http://url-to-license.com")
            .build();
    }
	
}
