package com.loansharkmss.LoanShark.v1.config;

import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.AuthService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import com.loansharkmss.LoanShark.v1.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private UserService userService;

    private JwtUtil jwtUtil;

    public JwtRequestFilter (UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwtHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (jwtHeader != null) {
            if (jwtHeader.startsWith("Bearer ")) {
                jwt = jwtHeader.substring(7);
                try {
                    username = jwtUtil.getUsernameFromJwt(jwt);
                } catch (IllegalArgumentException e) {
                    logger.warn("Unable to parse username from jwt");
                } catch (ExpiredJwtException e) {
                    logger.warn("Jwt has expired");
                }
            }
            else
                logger.warn("JwtHeader doesn't start with \"Bearer \"");
        }
        else
            logger.warn("JwtHeader is null");

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.loadUserByUsername(username);

            if (jwtUtil.isJwtValid(jwt, user)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(request, response);

    }
}
