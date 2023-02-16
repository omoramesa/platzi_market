package com.platzi.platzimarket.web.security;

import com.platzi.platzimarket.domain.service.PlatziUserDetailService;
import com.platzi.platzimarket.web.security.Filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PlatziUserDetailService platziUserDetailService;
    @Autowired
    JwtFilterRequest jwtFilterRequest;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(platziUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
               .anyRequest().authenticated().and().sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
