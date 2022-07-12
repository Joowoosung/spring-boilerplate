package com.jws.settings.security;

import com.jws.domain.main.dto.LoginAuth;
import com.jws.settings.error.exception.common.EtcException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTCheckFilter extends BasicAuthenticationFilter {

  private UkUserDetailsSvc userDetailsService;

  public JWTCheckFilter(AuthenticationManager authenticationManager, UkUserDetailsSvc userDetailsService) {
    super(authenticationManager);
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (bearer == null || !bearer.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }
    String token = bearer.substring("Bearer ".length());
    LoginAuth auth = JWTUtil.verify(token);
    if (auth.isSuccess()) {
//      BaseUser user = (BaseUser) userDetailsService.loadUserByUsername(auth.getUsername());
      UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
          auth.getUsername(), null, auth.getAuthorities()
      );
      userToken.setDetails(auth);
      SecurityContextHolder.getContext().setAuthentication(userToken);

      request.setAttribute("loginUser", auth);

      chain.doFilter(request, response);

    } else {
      response.setStatus(200);
      throw new EtcException("Token is not valid");
    }
  }

}
