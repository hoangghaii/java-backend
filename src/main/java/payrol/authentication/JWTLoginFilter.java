package payrol.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  protected JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
    super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
    setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
//    request.
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    return getAuthenticationManager()
        .authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult
  ) throws IOException, ServletException {
    TokenAuthenticationService.addAuthentication(response, authResult.getName());

    String authorizationString = response.getHeader("Authorization");

    System.out.println("Authorization String=" + authorizationString);
  }
}
