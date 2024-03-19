package com.example.LiquibaseProject.JWT;

import com.example.LiquibaseProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/login","/api/users/signin").permitAll()
                .antMatchers("/api/users/**").authenticated()
                .antMatchers("/api/user-companies/**","/api/companies/**").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Disable CSRF for simplicity (you can enable it if needed)
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/users/login").permitAll() // Allow POST requests to /login without authentication
//                .antMatchers(HttpMethod.GET, "/api/users").authenticated() // Allow GET requests to /api/users without authentication
//                .antMatchers(HttpMethod.POST, "/api/**").authenticated() // Require authentication for POST requests to /api/users/**
//                .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
//                .anyRequest().authenticated(); // Require authentication for all other requests
//
//        http.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }

}

