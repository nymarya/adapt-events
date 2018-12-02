package br.com.adapt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.adapt.framework.service.AdaptUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan("br.com.adapt.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired
	private AdaptUserDetailsService userDetailsService;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth.authenticationProvider(authenticationProvider());
	}

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
	        .antMatchers("/view/plugins**").permitAll() 
	        .antMatchers("/view/**").permitAll() 
	        .antMatchers("/user-register**").permitAll()
	        .anyRequest().authenticated()
	        .and()
        .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/checkin")
            .permitAll()
            .and()
        .logout()                                    
            .permitAll();
    }
	 
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}

}