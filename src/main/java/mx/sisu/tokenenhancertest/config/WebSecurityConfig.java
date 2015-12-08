package mx.sisu.tokenenhancertest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	
	@Configuration
	@Order(5)
	public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable();
			http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin_logout"))
					.invalidateHttpSession(true).logoutSuccessUrl("/admin/login.html");

			http.authorizeRequests()
					.antMatchers("/admin/login.html").permitAll().antMatchers("/admin/protected.html")
					.hasRole("ADMIN")
					.and().formLogin().loginPage("/admin/login.html")
					.loginProcessingUrl("/admin_login").defaultSuccessUrl("/admin/protected.html");

		}
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			//Data source for form based auth
			auth.inMemoryAuthentication().withUser("adminuser").password("adminpassword").roles("ADMIN");
		}
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//Data source for Oauth
		auth.inMemoryAuthentication().withUser("myuser").password("mypassword").roles("USER").and().withUser("test")
				.password("testpassword").roles("USER");
	}
}
