package com.rucsok.authenticaiton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
<<<<<<< HEAD:src/main/java/com/rucsok/authenticaiton/config/WebSecurityConfig.java
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
=======
>>>>>>> 1c79e552ee1440f71e7c7f00ac6e56896288a3e9:rucsok-resource/src/main/java/com/rucsok/authenticaiton/config/WebSecurityConfig.java

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthFailureHandler authFailureHandler;

	@Autowired
	AuthSuccessHandler authSuccessHandler;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
<<<<<<< HEAD:src/main/java/com/rucsok/authenticaiton/config/WebSecurityConfig.java
		.antMatchers(HttpMethod.HEAD).permitAll()
		.antMatchers(HttpMethod.GET, "/","/profile", "/check-rucsok", "/rucsok/**","/**/*.js", "/app/**/*.html", "/**/*.js.map", "/**/*.woff" , "/**/*.css", "/images/**").permitAll().antMatchers(HttpMethod.HEAD,  "/").permitAll()		
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.usernameParameter("sec-user")
		.passwordParameter("sec-password")
		.failureHandler(authFailureHandler)
		.successHandler(authSuccessHandler)
		.permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.deleteCookies("remember-me", "JSESSIONID")
		.permitAll()
		.and()
		.rememberMe();
=======
			.antMatchers(HttpMethod.GET, "/","/profile", "/check-rucsok", "/rucsok/**","/**/*.js", "/app/**/*.html", "/**/*.js.map" , "/**/*.css", "/images/**").permitAll() 
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers("/users").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/rucsok/**").authenticated()
			.antMatchers(HttpMethod.DELETE, "/rucsok/**").authenticated();
		
>>>>>>> 1c79e552ee1440f71e7c7f00ac6e56896288a3e9:rucsok-resource/src/main/java/com/rucsok/authenticaiton/config/WebSecurityConfig.java
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}