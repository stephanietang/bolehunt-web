package com.bolehunt.config;

import com.bolehunt.security.CustomUserDetailsService;
import com.bolehunt.security.JwtTokenProvider;
import com.bolehunt.security.filter.JwtAuthenticationFilter;
import com.bolehunt.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${security.bypass.urls}")
    private String bypassUrls;

    @Autowired
    WebSecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter() throws Exception {
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter();
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] bypassUrlArray = bypassUrls.split(",");
        http
                .csrf().disable() // TODO we should enable prevention from attacks of crsf
                .authorizeRequests()
                .antMatchers(bypassUrlArray).permitAll()
                .anyRequest().authenticated() // all other request require a logged in user

                .and()
                .logout()
                .logoutUrl("/logout") //the URL on which the clients should post if they want to logout
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)

                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider, customUserDetailsService))
                //.exceptionHandling() //default response if the client wants to get a resource unauthorized
                //.authenticationEntryPoint(new Http401AuthenticationEntryPoint("401"));

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // disable session creation
                ;
    }

}
