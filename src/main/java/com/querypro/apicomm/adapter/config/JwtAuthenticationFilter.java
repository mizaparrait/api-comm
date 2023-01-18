package com.querypro.apicomm.adapter.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull     FilterChain filterChain
    )
            throws ServletException, IOException {

            /*Es necesario en los hedears buscar el token para saber si existe.*/
            final String authHeader = request.getHeader("Authorization"); /*Es el header que contiene el token.*/
            final String jwt;
            final String userEmail;

            if(authHeader == null || !authHeader.startsWith("Bearer ")){/*Condicion que indica que debe iniciar con Bearer*/
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);/*Extraemos la cadena jwt desde posicion 7 ("Bearer)*/
            userEmail = jwtService.extractUsername(jwt);/*Extraemos el email como usuario.*/

            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if(jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken );
                }
            }
            filterChain.doFilter(request, response);
    }
}
