package com.jws.domain.main.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "reject_player")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RejectPlayer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String username;
  @Column(name = "name", length = 30, nullable = false)
  private String name;
  @Column(length = 20)
  private String nick;
  @Column(length = 20)
  private String phone;
  @Column(length = 100)
  private String email;

  @Column(length = 60)
  private String signupIp;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", nullable = false, updatable = false)
  private Date signupDate;


  public RejectPlayer(Player player) {
    JwsUser user = player.getUser();
    this.username = user.getUsername();
    this.name = user.getName();
    this.nick = player.getNick();
    this.phone = player.getPhone();
    this.email = player.getEmail();
    this.signupIp = player.getSignupIp();
    this.signupDate = user.getCreateDate();
  }
}
