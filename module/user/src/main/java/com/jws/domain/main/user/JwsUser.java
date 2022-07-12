package com.jws.domain.main.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jws.dto.user_converter.UserRole;
import com.jws.dto.user_converter.UserRoleConverter;
import com.jws.dto.user_converter.UserStatus;
import com.jws.dto.user_converter.UserStatusConverter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(name = "USER_USERNAME_UNIQUE", columnNames = {"username", "site_info_id"})
}, indexes = {
    @Index(name = "IDX_CREATE_DATE", columnList = "create_date")
//    @Index(name = "IDX_LASTLOGINDATE", columnList = "lastLoginDate"),
})
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwsUser implements UserDetails, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //  @Size(min = 6, max = 20, message = "Username have to be grater than 8 characters")
  @Column(length = 50, nullable = false)
  private String username;
  @Getter(onMethod_ = @JsonIgnore)
  @Setter(onMethod_ = @JsonProperty)
  @Column(nullable = false, length = 200)
  private String password;
  @Setter
  @Column(name = "name", length = 30, nullable = false)
  private String name;
  @Convert(converter = UserRoleConverter.class)
  @Column(name = "role", columnDefinition = "CHAR(3)", nullable = false)
  private UserRole role;
  @Getter(onMethod_ = @JsonIgnore)
  @Column(length = 16)
  private String otpKey;
  @Setter
  @Convert(converter = UserStatusConverter.class)
  @Column(name = "status", columnDefinition = "CHAR(1)", nullable = false)
  private UserStatus status;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", nullable = false, updatable = false)
  private Date createDate;
  @Setter
  @Column(columnDefinition = "CHAR(1)")
  private String isTest = "N"; // "Y" 일경우 테스트
  @Temporal(TemporalType.TIMESTAMP)
  @Column
  private Date lastLoginDate;

  @Getter(onMethod_ = @JsonIgnore)
  @Setter(onMethod_ = @JsonIgnore)
  @Transient
  private String pageType;

  public JwsUser(Long id) {
    this.id = id;
  }

  @Builder
  public JwsUser(Long id, String username, String password, String name, UserRole role, String otpKey,
      UserStatus status, String isTest) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
    this.role = role;
    this.otpKey = otpKey;
    this.status = status;
    this.isTest = isTest;
  }

  public boolean getIsOtp() {
    return StringUtils.isNotEmpty(this.otpKey);
  }

  @PrePersist
  private void onCreate() {
    this.createDate = new Date();
  }

  @JsonIgnore
  public String getPrincipal() {
    return this.username + "__principal__";
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // --> 설정을 LoginAuth에서 함
    return null;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() { // 회원탈퇴
    return !this.status.equals(UserStatus.WITHDRAW);
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() { // 회원 정지
    return !this.status.equals(UserStatus.DENY);
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return !this.status.equals(UserStatus.LOADED);
  }
}
