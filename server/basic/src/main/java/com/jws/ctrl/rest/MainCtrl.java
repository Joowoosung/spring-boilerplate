package com.jws.ctrl.rest;

import com.jws.domain.main.dto.LoginAuth;
import com.jws.dto.user_converter.UserRole;
import com.jws.settings.annotation.LoginUser;
import com.jws.settings.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping(value = "/api")
@RestController
@RequiredArgsConstructor
public class MainCtrl {

  private String updateTime;


  @Value("${spring.profiles.active}")
  public void setUpdateTime(String profiles) {
    this.updateTime = "[" + profiles.toUpperCase() + "] " + LocalDateTime.now().format(DateUtil.dateTimeAllFormat);
  }

  @GetMapping("/server_version")
  public ResponseEntity getServerUpdateTime() {
    return ResponseEntity.ok(this.updateTime);
  }

  // FIXME: 로그인 시점에 사이트 정보 (사용자페이지인지 아닌지 구분할만한)를 저장해놓고 그걸로 분기 처리하게 수정
  @GetMapping("/me")
  public ResponseEntity create(@LoginUser LoginAuth auth) {
    if (auth.getRole().matches(UserRole.PLAYER.getCode() + "|" + UserRole.PARTNER.getCode())) {
//      return ResponseEntity.ok(this.memberSvc.get("Me", auth.getId()));
    } else {
//      return ResponseEntity.ok(this.userSvc.get(auth.getId()));
    }
    return null;
  }

}
