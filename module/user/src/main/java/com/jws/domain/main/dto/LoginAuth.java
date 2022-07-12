package com.jws.domain.main.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jws.dto.user_converter.UserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@ToString
@NoArgsConstructor
@Getter
public class LoginAuth {

  @ApiModelProperty(required = false, hidden = true)
  private boolean success;
  @ApiModelProperty(required = false, hidden = true)
  private Long id;
  @ApiModelProperty(required = false, hidden = true)
  private String username;
  @ApiModelProperty(required = false, hidden = true)
  private String role;
  @ApiModelProperty(required = false, hidden = true)
  private String pageType;

  @Builder
  public LoginAuth(boolean success, Long id, String username, String role, Long siteInfoId, String pageType) {
    this.success = success;
    this.id = id;
    this.username = username;
    this.role = role;
    this.pageType = pageType;
  }


  public LoginAuth(Map<String, String> payload, boolean success) {
    this.success = success;
    this.id = Long.valueOf(payload.get("id"));
    this.username = payload.get("username");
    this.role = payload.get("role");
    this.pageType = payload.get("pageType");
  }

  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (this.role.equals(UserRole.PARTNER.getCode()) && !StringUtils.equals(this.pageType, "A")) {
      authorities.add(new SimpleGrantedAuthority(UserRole.PLAYER.getDesc()));
    } else {
      authorities.add(new SimpleGrantedAuthority(UserRole.parse(this.role).getDesc()));
    }
    return authorities;
  }

  @JsonIgnore
  public String getPrincipal() {
    return this.username + "__principal__";
  }

//  public LoginUserDto.Request getLoginRequest() {
//    return LoginUserDto.Request.builder()
//        .username(this.username).siteCode(this.siteCode).profileType(this.role)
//        .build();
//  }
}
