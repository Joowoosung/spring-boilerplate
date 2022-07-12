package com.uk.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserResponse {

  private Long id;
  private String username;
  private String name;
  private String role;
  private String accessToken;
  private String refreshToken;


  // refershToken 무효화 기능
  @Builder
  public LoginUserResponse(Long id, String username, String name, String role, String accessToken,
      String refreshToken) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.role = role;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
