package com.spk.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.spk.demo.util.UserRole;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/welcome", "/signup", "/login").permitAll().anyRequest()
				.authenticated();
		http.authorizeRequests().antMatchers("/userIfo").access("hasRole('" + UserRole.USER_ROLE + "')");
		http.authorizeRequests().antMatchers("/userIfo").access("hasRole('" + UserRole.ADMIN_ROLE + "')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin().loginPage("/login").defaultSuccessUrl("/userInfo")
				.failureUrl("/login?error").and().logout().invalidateHttpSession(true).logoutUrl("/logout")
				.logoutSuccessUrl("/");

	}

}
