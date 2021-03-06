package com.jws.settings.config.resolver;

import com.jws.settings.annotation.LoginUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(LoginUser.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//            User user = (User) request.getAttribute(AUTH_USER_KEY);

    return request.getAttribute("loginUser");
  }


  /*public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    User user = (User) request.getAttribute(AUTH_USER_KEY);

    LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);

    if (loginUser == null || user == null) {
      throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
    }

    return user;
  }*/
}
