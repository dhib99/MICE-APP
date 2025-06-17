package jwtSecurity.example.jwtDemo.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired

    private UserDetailsService userDetailsService;

    //Constructor
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }


    // This method is executed for every request intercepted by the filter.
    //And, it extract the token from the request header and validate the token.
    @Override

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Ignore JWT verification for public endpoints
        String path = request.getServletPath();
        if (path.equals("/api/auth/signup") || path.equals("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get JWT token from HTTP request
        String token = getTokenFromRequest(request);

        // Validate Token
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // Get username from token
            String username = jwtTokenProvider.getUsername(token);

            // Get userId from token
            Long userId = jwtTokenProvider.getUserId(token);

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create authentication token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // Optionally, you can add the userId in a request attribute for later use in controllers
            request.setAttribute("userId", userId);
        }

        filterChain.doFilter(request, response);
    }


    // Extract the token
    private String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
