package kth.iv1201.recruitment.config;

import kth.iv1201.recruitment.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security configuration for web security. Authorization and authentication for specific routes.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value(value = "${spring.websecurity.debug}")
	private boolean webSecurityDebug;


	/**
	 * Configuration for Web security.
	 *
	 * @param http Used for giving authorization and authentication and as well of exception handling of failed url.
	 *
	 * @throws Exception If one of the parameter are not correct configured.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/applicants/**").access("hasRole('ROLE_recruiter')")
        .antMatchers("/positions").access("hasRole('ROLE_applicant')")
        .and().formLogin().loginPage("/login").failureUrl("/login-error").permitAll()
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").clearAuthentication(true).deleteCookies().invalidateHttpSession(true).permitAll();
	}

	/**
	 * Configure for Authentication Manager.
	 *
	 * @param auth Authentication manager builder for authenticate provider.
	 *
	 * @throws Exception When parameter are not made correctly.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/**
	 * Enable Debug in Spring Security.
	 *
	 * @param web WebSecurity object.
	 *
	 * @throws Exception When parameter are not made correctly.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(webSecurityDebug);
	}

	/**
	 * Instantiate a UserDetailsService
	 *
	 * @return a newly created Security user detail service.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityUserDetailsService();
	}

	/**
	 * Sets configuration for the provider. Provider of UserDetails. Provider of Password Encoder.
	 *
	 * @return Configured provider.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}

	/**
	 * Select type of encoding.
	 *
	 * @return Instance of BCryptPasswordEncoder.
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
