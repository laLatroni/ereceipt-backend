//package com.ereceipt.CAZAEORPROJECT.LoginSucess;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collection;
//
//public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        System.out.println("USERNAME" + userDetails.getUsername());
//
//        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//        authorities.forEach(auth -> System.out.println(auth.getAuthority()));
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}
