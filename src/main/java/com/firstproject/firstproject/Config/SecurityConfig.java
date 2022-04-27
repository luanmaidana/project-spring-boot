package com.firstproject.firstproject.Config;

import com.firstproject.firstproject.Security.JWTAuthenticationFilter;
import com.firstproject.firstproject.Security.JWTAuthorizationFilter;
import com.firstproject.firstproject.Security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    public static final String[] private_matchers = {

        "/api/viagem/**",
        "/api/pacotes/**",
        "/api/destino/**",
       // "/api/usuarios/**",
        "/login"

    };

    public static final String[] public_matchers_get = {

        "/api/viagem/**",
        "/api/pacotes/**",
        "/api/destino/**"

    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
       // http.authorizeHttpRequests().antMatchers(private_matchers).permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET ,public_matchers_get).permitAll().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(jwtUtil, authenticationManager()));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService)); 
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
            
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
