package co.com.api.filter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.core.authority.AuthorityUtils;

@Configuration
public class WebSecurityConfig extends org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter{

	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		/*
		 Build this logic using a repostory methods
		 */
		return null;
	}

	@Bean
	@SuppressWarnings("deprecation")
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}