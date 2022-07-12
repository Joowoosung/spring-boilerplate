package com.jws.domain.main.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "user_player")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private JwsUser user;

  @Column(length = 20)
  private String nick;
  @Column(length = 20)
  private String phone;
  @Column(length = 100)
  private String email;
//  @Column(length = 50)
//  private String bankName;
//  @Column(length = 50)
//  private String accountNum;
//  @Column(length = 30)
//  private String accountName;
  @Column(columnDefinition = "CHAR(1)")
  private String isPartner; // 일반회원일경우: 1, 파트너사계정일경우: 2
  @Column(columnDefinition = "CHAR(1)")
  private String isTrader; // Y / N

  @Setter
  @Column(length = 60)
  private String signupIp;
  @Column
  private Double fee = 100d; // 사용자가 너무 잘할때 파트너가 과도한 수수료를 받아간다. 그럴때 fee를 낮쳐서 얘를 통한 수수료를 퍼센트비율로 낮춤
  @Setter
  @Column(length = 50)
  private String depositMoney = "0"; // 누적입금액
  @Setter
  @Column(length = 50)
  private String withdrawMoney = "0"; // 누적출금액

  @Setter
  @Column(length = 300)
  private String memo;

  @Builder
  public Player(Long id, JwsUser user, String nick, String phone, String email,
       String partnerInfo, String isPartner, String signupIp,
      Double fee, String memo) {
    this.id = id;
    this.user = user;
    this.nick = nick;
    this.phone = phone;
    this.email = email;
    this.isPartner = isPartner;
    this.signupIp = signupIp;
    this.fee = fee;
    this.memo = memo;
  }

  public Player(Long id, JwsUser user) {
    this.id = id;
    this.user = user;
  }

}
