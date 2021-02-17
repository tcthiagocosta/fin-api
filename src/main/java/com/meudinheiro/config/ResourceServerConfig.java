package com.meudinheiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/usuarios").permitAll()
                .antMatchers("/api/usuarios/**").hasRole("ADMIN")
                .antMatchers("/api/clientes/**").hasAnyRole("CLIENTE","ADMIN")
                .antMatchers("/api/conta/**").hasAnyRole("ADMIN")
                .antMatchers("/api/cartao/**").hasAnyRole("ADMIN")
                .antMatchers("/api/categoria/**").hasAnyRole("ADMIN")
                .antMatchers("/api/subCategoria/**").hasAnyRole("ADMIN")
                .antMatchers("/api/faturaCartao/**").hasAnyRole("ADMIN")
                .antMatchers("/api/lancamento/**").hasAnyRole("ADMIN")
                .antMatchers("/api/transferencia/**").hasAnyRole("ADMIN")
                .antMatchers("/api/lancamentoFatura/**").hasAnyRole("ADMIN")
                .anyRequest().denyAll();
    }
}
