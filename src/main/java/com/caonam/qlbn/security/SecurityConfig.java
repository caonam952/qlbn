package com.caonam.qlbn.security;

import com.caonam.qlbn.security.filter.CustomAuthenticationFilter;
import com.caonam.qlbn.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll()
                .antMatchers(GET, "/api/user/**").hasAnyAuthority("USER")
                .antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ADMIN")
                .antMatchers(DELETE, "/api/user/**", "/api/role/**").hasAnyAuthority("ADMIN")
                .antMatchers(PUT, "/api/user/update/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/employees/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/api/medicines/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/api/patients/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/api/prescriptions/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/api/prescription_details/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/api/records/**").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(customAuthenticationFilter).addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//
//        return authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests().anyRequest().permitAll()
//                .and()
//                .addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        return http.build();
//    }


}
