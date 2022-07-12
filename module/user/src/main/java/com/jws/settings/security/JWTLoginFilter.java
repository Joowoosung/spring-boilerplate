//package com.jws.settings.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.jws.domain.main.dto.LoginAuth;
//import com.jws.domain.main.user.AccessLog;
//import com.jws.settings.constants.Etc;
//import com.jws.settings.error.exception.common.EtcException;
//import com.jws.settings.utils.IpUtil;
//import lombok.SneakyThrows;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDateTime;
//
//public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
//
//  private final ObjectMapper objectMapper;
//  private final UkUserDetailsSvc userDetailsSvc;
//  private final AccessLogSvc accessLogSvc;
//  private final UserSvc userSvc;
//  private final GoogleOtpSvc googleOtpSvc;
//
//  public JWTLoginFilter(AuthenticationManager authenticationManager, UkUserDetailsSvc userDetailsSvc,
//      AccessLogSvc accessLogSvc, UserSvc userSvc, GoogleOtpSvc googleOtpSvc) {
//    this.objectMapper = new ObjectMapper();
//    this.userDetailsSvc = userDetailsSvc;
//    this.accessLogSvc = accessLogSvc;
//    this.userSvc = userSvc;
//    this.googleOtpSvc = googleOtpSvc;
//    setAuthenticationManager(authenticationManager);
//    setFilterProcessesUrl("/api/signin"); // /oauth/token
//  }
//
//
//  @SneakyThrows
//  @Override
//  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//      throws AuthenticationException {
//    LoginDto.Request req = objectMapper.readValue(request.getInputStream(), LoginDto.Request.class);
////    request.setAttribute("profileType", req.getProfileType());
//    if (req.getRefreshToken() == null) {
//      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//          this.getLoadUserByUsername(req.getUsername(), req.getPageType()),
//          req.getPassword(), null
//      );
//      // user details...
//
//      JwsUser user = this.userSvc.getByUsername(req.getUsername());
//
//      String connectIp = request.getHeader("CF-Connecting-IP");
//      if (StringUtils.isEmpty(connectIp)) connectIp = request.getHeader("X-FORWARDED-FOR");
//      if (StringUtils.isEmpty(connectIp)) connectIp = IpUtil.getPublicIp();
//
//      if (user != null) {
//        if (StringUtils.isNotBlank(user.getOtpKey())) {
//          String code = req.getOtpKey();
//          if (StringUtils.isBlank(code) || !this.googleOtpSvc.checkCode(code, user.getOtpKey())) {
//            throw new EtcException("OtpNotMatched");
//          }
//        }
//
//        this.accessLogSvc.save(
//            new AccessLog(user.getUserId(), user.getUsername(), user.getRole().getCode(), connectIp,
//                request.getHeader("CF-IPCountry"), request.getHeader("USER-AGENT")));
//
//        this.userSvc.updateLastLoginDate(user.getUserId(), LocalDateTime.now());
//      }
//      try {
//        Authentication auth = getAuthenticationManager().authenticate(token);
//        return auth;
//      } catch (Exception e) {
//        e.printStackTrace();
//
//        response.setStatus(401);
//        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//
//        response.getWriter().print(
//            WebUtil.getResponseString(request, 401, "Unauthorized", "아이디 또는 비밀번호가 일치하지 않습니다.")
//        );
//        return null;
//      }
//    } else {
//      // refreshToken 유효성확인..
//      // 로그아웃시 refreshToken을 서버에 저장 그럼 그거로는 로그인안됨
//      // 클라이언트에 리프래쉬토큰을 주면안돼..
//      LoginAuth auth = JWTUtil.verify(req.getRefreshToken());
//      if (auth.isSuccess()) {
//        JwsUser user = (JwsUser) userDetailsSvc.loadUserByUsername(
//            this.getLoadUserByUsername(auth.getUsername(), auth.getPageType())
//        );
//        // TODO: 만약에 회원의 상태가 변경되었따면 익셉션발생해야함
//        return new UsernamePasswordAuthenticationToken(
//            user, user.getAuthorities()
//        );
//      } else {
////        throw new EtcException("refresh token expired");
//        response.setStatus(401);
//        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
////        response.getOutputStream().write("Refresh Token is Expired".getBytes(StandardCharsets.UTF_8));
//        response.getWriter().print(
//            WebUtil.getResponseString(request, 403, "TokenExpired", "토큰이 만료되었습니다.")
//        );
//        return null;
//      }
//    }
//  }
//
//  private String getLoadUserByUsername(String username, String pageType) {
//    // NOTE: 관리자 테스트 하려고 바꿈
//    if (StringUtils.isEmpty(pageType)) {
//      pageType = "P";
//    }
//    return String.format("%s%s%s", username.trim(), Etc.LOGIN_SEPARTOR, pageType);
//  }
//
//  @Override
//  protected void successfulAuthentication(
//      HttpServletRequest request,
//      HttpServletResponse response,
//      FilterChain chain,
//      Authentication authResult) throws IOException, ServletException {
//    // TODO: 여기서 사이트 구분 및 등등 처리 하면 됨
//
//    JwsUser user = (JwsUser) authResult.getPrincipal();
//    LoginDto.Response res = LoginDto.Response.builder()
//        .accessToken(JWTUtil.makeAuthToken(user)).refreshToken(
//            JWTUtil.makeRefreshToken(user))
//        .expiresIn(JWTUtil.AUTH_TIME)
//        .user(user).build();
//
//    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//    response.getOutputStream().write(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsBytes(res));
//  }
//
//  @Override
//  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//      AuthenticationException failed) throws IOException, ServletException {
////    super.unsuccessfulAuthentication(request, response, failed);
//    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//    response.getOutputStream().write(objectMapper.writeValueAsBytes(500));
//  }
//}
