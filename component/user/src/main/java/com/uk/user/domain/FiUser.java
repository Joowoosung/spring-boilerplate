package com.uk.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "fi_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FiUser implements UserDetails, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;
  //  @Size(min = 6, max = 20, message = "Username have to be grater than 8 characters")
  @Column(length = 50, nullable = false)
  private String username;
  @Getter(onMethod_ = @JsonIgnore)
  @Setter(onMethod_ = @JsonProperty)
  @Column(nullable = false, length = 200)
  private String password;
  @Column(name = "name", length = 30, nullable = false)
  private String name;
  @Column(nullable = false, length = 3)
  private String role;
  @Getter(onMethod_ = @JsonIgnore)
  @Column(length = 16)
  private String otpKey;
  // S: 승인완료, D: 승인거절
//  @Column(name = "status", columnDefinition = "CHAR(1)", nullable = false)
  @Column
  private boolean enabled;
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createDate;
  @Column(columnDefinition = "CHAR(1)")
  private String isTest; // "Y" 일경우 테스트

  @Builder
  public FiUser(Long id, String username, String password, String name, String role, String otpKey, boolean enabled,
      Date createDate, String isTest) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
    this.role = role;
    this.otpKey = otpKey;
    this.enabled = enabled;
    this.createDate = createDate;
    this.isTest = isTest;
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (this.role.equals("P")) {
      authorities.add(new SimpleGrantedAuthority("PLAYER"));
    } else if (this.role.equals("A")) {
      authorities.add(new SimpleGrantedAuthority("ADMIN"));
    }
    return authorities;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() { // 회원탈퇴
    return enabled;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() { // 회원 정지
    return enabled;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return enabled;
  }
}
