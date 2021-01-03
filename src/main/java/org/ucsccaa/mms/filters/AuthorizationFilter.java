package org.ucsccaa.mms.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.ucsccaa.mms.services.AuthorService;
import org.ucsccaa.mms.services.impl.AuthenticationServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/member/*", "/staff/*"})
@Order(value = 2)
public class AuthorizationFilter implements Filter {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = ((HttpServletRequest)request).getHeader("authorization");
        String level = authenticationService.loadUserByUsername(authenticationService.getUserNameFromToken(token)).getStaff().getAuthorization().getLevel().toString();
        String method = ((HttpServletRequest) request).getMethod().toUpperCase();
        String uri = ((HttpServletRequest) request).getRequestURI().substring(1).toUpperCase();
        try {
            if (!authorService.checkAuthority(level,method,uri)){
                ((HttpServletResponse)response).sendError(401, "Unauthorized");
                return;
            }
        } catch (IOException e) {
            ((HttpServletResponse)response).sendError(500, "INVAILD TOKEN");
            return;
        }
        chain.doFilter(request, response);
    }
}