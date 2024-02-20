package com.shopping.ecommerce.config;

import com.shopping.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {
    private static final String AUTHOTRIZATION = "authorization";
    @Autowired
    CustomerService customerService;
    @Autowired
    UserDetailsService userDetailsService;
    private final  JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader(AUTHOTRIZATION);
        final String userEmail;
        final String jwtToken;
//        Customer userDetails;
        UserDetails userDetails;
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken=authHeader.substring(7);
        userEmail=jwtUtil.extractUsername(jwtToken);
        if(userEmail!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            userDetails= userDetailsService.loadUserByUsername(userEmail);
            final boolean isTokenValid= jwtUtil.isTokenValid(jwtToken,userDetails);
            if(isTokenValid){
               UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getServletPath().startsWith("/api");
//    }
}
