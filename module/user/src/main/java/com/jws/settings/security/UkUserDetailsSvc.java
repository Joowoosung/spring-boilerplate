package com.jws.settings.security;

import com.jws.domain.main.user.JwsUser;
import com.jws.dto.user_converter.UserRole;
import com.jws.repo.main.user.JwsUserRepo;
import com.jws.settings.constants.Etc;
import com.jws.settings.error.UkAssert;
import com.jws.settings.error.exception.common.EtcException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UkUserDetailsSvc implements UserDetailsService {

  private final JwsUserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

    // 관리자 계정 (일단 현재는 개발 안함) ! 본사도 파트너이다
    String[] split = input.split(Etc.LOGIN_SEPARTOR);
    String username = split[0], pageType = split[1];

    JwsUser user = this.userRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username + "은 존재하지 않는 계정입니다."));

    if (pageType.equals("A")) {
      UkAssert.isFalse(user.getRole().equals(UserRole.PLAYER), new EtcException("일반 사용자는 접근불가"));
    } else {
      UkAssert.isTrue(
          (user.getRole().equals(UserRole.PLAYER) || user.getRole().equals(UserRole.PARTNER)),
          new EtcException("관리자는 접근불가"));
    }
    user.setPageType(pageType);
    return user;
  }
}
