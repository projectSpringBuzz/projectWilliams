package com.wjma.core.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wjma.spring.security.MyBasicAuthenticationEntryPoint;

/**
 * Enable Spring security configuration
 * @author P023666
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Custom entry point to 401 status
	 */
	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	/**
	 * Data in memory to http basic
	 * user is: user
	 * Password is: secret
	 * visit http://www.devglan.com/online-tools/bcrypt-hash-generator
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("$2a$04$GrEFNY3igiJpvK4w/YL.vea9zOSbjnnVfMoen9jLL4yqLuHOrVkNu").authorities("ROLE_USER");
	}

	/**
	 * CSRF security disabled and all urls of project are securized (need authentication)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "favicon.ico").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
	}

	/**
	 * Encoder/Decoder for password (spring use it)
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}