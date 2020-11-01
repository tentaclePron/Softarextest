package by.felix.softarextest.filters;

import by.felix.softarextest.config.jwt.JwtProvider;
import by.felix.softarextest.customException.APPException;
import by.felix.softarextest.entities.User;
import by.felix.softarextest.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

/**
 * Authorise jwt filter
 */
@Component
@Log
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    private JwtProvider jwtProvider;
    private UserRepository userRepository;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
    @Autowired
    public void setUserCrud(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            User user = userRepository.findByUsername(userLogin).orElseThrow(() -> new APPException("User has been deleted"));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}