package com.wjma.core.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.wjma.spring.security.LoggingAccessDeniedHandler;

/**
 * Enable Spring security configuration
 * 
 * @author P023666
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

	/**
	 * Configuration for rest api controller
	 * @author wjma
	 *
	 */
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable()
				.antMatcher("/v0/**").authorizeRequests().anyRequest().authenticated().and().httpBasic();
		}
	}

	/**
	 * Configuration for web app
	 * @author wjma
	 *
	 */
	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		/**
		 * Custom entry to access denied
		 */

		@Autowired
		private LoggingAccessDeniedHandler accessDeniedHandler;

		/**
		 * CSRF security enable and all urls of project are securized (need
		 * authentication)
		 */
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/webjars/**", "favicon.ico", "registration").permitAll()
				.anyRequest().authenticated()
				.and()
					.formLogin()
						.loginPage("/login")
						.failureUrl("/login?error=true").permitAll()
				.and()
					.logout()
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll()
				.and()
					.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		}

	}

	/**
	 * Data in memory to http basic and form login, user is: user and Password is: secret 
	 * visit
	 * http://www.devglan.com/online-tools/bcrypt-hash-generator
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user")
				.password("$2a$04$GrEFNY3igiJpvK4w/YL.vea9zOSbjnnVfMoen9jLL4yqLuHOrVkNu").authorities("ROLE_USER");
	}

	/**
	 * Encoder/Decoder for password (spring use it)
	 * 
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}