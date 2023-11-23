package com.ereceipt.CAZAEORPROJECT.Security;


import com.ereceipt.CAZAEORPROJECT.Custom_Logout;
//import com.ereceipt.CAZAEORPROJECT.LoginSucess.LoginSuccessHandler;
import com.ereceipt.CAZAEORPROJECT.jwt.JWTAuthenticationFilter;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BASIC SECURITY HANDLING, STILL CONFIGURING.
 */
@Configuration
@EnableWebSecurity
public class CazaEorSecurityConfig{

//    private static final String[] SECURED_URLs = {   "/roles/**", "/api/v1/eor/**"};
//
//    private static final String[] UN_SECURED_URLs = {
//            "/api/v1/eor/user-register",
//            "/api/v1/**"
//    };

    @Autowired
    private JWTAuthenticationFilter authenticationFilter;
@Autowired
    private  Custom_Logout customLogout;

@Autowired
private CazaEorUserService cazaEorUserService;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(cazaEorUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()

                    .authorizeRequests()

                    .antMatchers( "/login","/roles/**").permitAll().anyRequest().authenticated()
//                    .antMatchers("/api/v1/eor/addTransactions").authenticated()
                    .and()
//                    .formLogin().permitAll().and()
//                    .logout().logoutUrl("/doLogout")
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)


         //           .authenticated().anyRequest().hasAuthority("ADMIN")

                    //DMIN").anyRequest().authenticated()

    //             .antMatchers(SECURED_URLs).permitAll()
    //              .antMatchers(UN_SECURED_URLs).hasAuthority("ROLE_USER").anyRequest().authenticated()
    //
    //
    //
    //////
    //                .and()
    //                .formLogin().loginPage("/login").permitAll()
    //                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout()
                    .logoutUrl("/api/v1/logout")
                    .addLogoutHandler(customLogout)
                    .logoutSuccessHandler(
                            (request, response, authentication) -> SecurityContextHolder.clearContext()
                    );

            return http.build();
        }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }



}

