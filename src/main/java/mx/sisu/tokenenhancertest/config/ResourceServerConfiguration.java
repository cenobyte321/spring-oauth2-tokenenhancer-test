package mx.sisu.tokenenhancertest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	@Autowired
	TokenStore tokenStore;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("resource").tokenStore(tokenStore);
	}

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		System.out.println("ResourceServerConfiguration LOADED!!!");
		http.requestMatchers().antMatchers("/api/**")
			.and().authorizeRequests().antMatchers("/api/**").authenticated();
	}

}
