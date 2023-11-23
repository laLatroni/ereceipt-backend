package com.ereceipt.CAZAEORPROJECT.jwt;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ereceipt.CAZAEORPROJECT.Security.CazaEorUserDetails;
import com.ereceipt.CAZAEORPROJECT.Security.CazaEorUserService;
import com.ereceipt.CAZAEORPROJECT.Token.TokenRepo;
import com.ereceipt.CAZAEORPROJECT.Token.Tokens;
import lombok.var;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT
 */

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final CazaEorUserService cazaEorUserService;
    private final TokenRepo tokenRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, ServletException {

      final   String authHeader = request.getHeader("Authorization");
      final   String jwt;
      final   String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
////            jwt = authHeader.substring(7);
//            userName = jwtService.extractUsernameFromToken(jwt);
        }
        jwt = authHeader.substring(7);
        userName = jwtService.extractUsernameFromToken(jwt);
        if (userName != null & SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.cazaEorUserService.loadUserByUsername(userName);
                var isTokenvalid = tokenRepo.findByToken(jwt)
                        .map(tokens -> !tokens.isExpired() && !tokens.isRevoked())
                        .orElse(false);
            if(jwtService.validateToken(jwt, userDetails) && isTokenvalid) {
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

