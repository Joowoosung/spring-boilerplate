package com.jws.domain.main.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "access_log", indexes = {
    @Index(name = "IDX_DATE", columnList = "createDate")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Long userId;
  @Column(length = 50, nullable = false)
  private String username;
  @Column(nullable = false, length = 3)
  private String role;
  @Column(length = 60)
  private String cfConnectingIp;
  @Column(length = 60)
  private String cfIpCountry;
  @Column(length = 300)
  private String userAgent;
  @Column(columnDefinition = "DATETIME(3) NOT NULL")
  private Date createDate;

  public AccessLog(Long userId, String username, String role,
      String cfConnectingIp, String cfIpCountry, String userAgent) {
    this.userId = userId;
    this.username = username;
    this.role = role;
    this.cfConnectingIp = cfConnectingIp;
    this.cfIpCountry = cfIpCountry;
    this.userAgent = userAgent;
  }

  @PrePersist
  private void onCreate() {
    this.createDate = new Date();
  }
}
