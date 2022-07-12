package com.jws.domain.main.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jws.domain.main.user.JwsUser;
import com.jws.domain.main.wallet.converter.WalletType;
import com.jws.domain.main.wallet.converter.WalletTypeConverter;
import com.jws.settings.constants.Currency;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "wallet", uniqueConstraints = {
    @UniqueConstraint(
        // HELP: currency를 추가해야될수도 있음 (그렇게 되면 Concurrency 서비스들 수정해야함 (돈넣는 서비스))
        columnNames = {"user_id", "type"}
    )
})
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter(onMethod_ = @JsonIgnore)
  @Setter(onMethod_ = @JsonProperty)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private JwsUser user;
  @Convert(converter = WalletTypeConverter.class)
  @Column(name = "type", columnDefinition = "CHAR(1)", nullable = false)
  private WalletType type;
  @Column(length = 10)
  private String currency; // USDT or BKT
  @Column(name = "precision_value")
  private int precision; // 1300.00 또는 고정값
  @Setter
  @Column(columnDefinition = "DECIMAL(30,8)")
  private BigDecimal point;
  @Setter
  @Column(columnDefinition = "DECIMAL(30,8)")
  private BigDecimal waitPoint; // 대기포인트
  @Setter
  @Column(columnDefinition = "DECIMAL(30,8)")
  private BigDecimal gameWaitPoint; // 게임중인 포인트
  @Setter
  @Column(columnDefinition = "DECIMAL(50,8)")
  private BigDecimal profitPoint; // 누적수익 TODO:
  @Setter
  @Column(columnDefinition = "DECIMAL(50,8)")
  private BigDecimal totalTradeMoney; // 누적총거래금액
  @Setter
  @Column(columnDefinition = "DECIMAL(50,8)")
  private BigDecimal totalFeePoint; // 누적수수료금액
  @Setter
  @Column(columnDefinition = "DECIMAL(50,8)")
  private BigDecimal totalDepositMoney; // 누적입금액
  @Setter
  @Column(columnDefinition = "DECIMAL(50,8)")
  private BigDecimal totalWithdrawMoney; // 누적출금액

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date", nullable = false, updatable = false)
  private Date createDate;

  @Column(length = 200)
  private String address;
  @Column(length = 200)
  private String coinName; // binance, ripple, 카카오뱅크,

  @Transient
  @Setter
  private BigDecimal pnl;

  @Builder
  public Wallet(Long id, JwsUser user, WalletType type, String currency, int precision, BigDecimal point,
      BigDecimal waitPoint, BigDecimal gameWaitPoint, BigDecimal profitPoint, BigDecimal totalTradeMoney,
      BigDecimal totalFeePoint, BigDecimal totalDepositMoney, BigDecimal totalWithdrawMoney, Date createDate,
      String address, String coinName, BigDecimal pnl) {
    this.id = id;
    this.user = user;
    this.type = type;
    this.currency = currency;
    this.precision = precision;
    this.point = point;
    this.waitPoint = waitPoint;
    this.gameWaitPoint = gameWaitPoint;
    this.profitPoint = profitPoint;
    this.totalTradeMoney = totalTradeMoney;
    this.totalFeePoint = totalFeePoint;
    this.totalDepositMoney = totalDepositMoney;
    this.totalWithdrawMoney = totalWithdrawMoney;
    this.createDate = createDate;
    this.address = address;
    this.coinName = coinName;
    this.pnl = pnl;
  }

  // 최초 회원가입시 생기는 계좌
  public Wallet(Long userId, WalletType type, String binaryCurrency) {
    this.user = new JwsUser(userId);
    this.type = type;
    this.point = BigDecimal.ZERO;
    this.waitPoint = BigDecimal.ZERO;
    this.gameWaitPoint = BigDecimal.ZERO;
    this.profitPoint = BigDecimal.ZERO;
    this.totalFeePoint = BigDecimal.ZERO;
    this.totalTradeMoney = BigDecimal.ZERO;
    this.totalDepositMoney = BigDecimal.ZERO;
    this.totalWithdrawMoney = BigDecimal.ZERO;
    if (binaryCurrency.equals(Currency.BINARY)) {
      this.currency = Currency.BINARY;
      this.precision = 0;
    } else {
      this.precision = 8;
      this.currency = type.equals(WalletType.FUTURES) ? "USDT" : type.getDesc();
      this.coinName = this.currency;
    }
  }

  public Wallet(Long userId, WalletType type, String address, String coinName) {
    this.user = new JwsUser(userId);
    this.type = type;
    this.point = BigDecimal.ZERO;
    this.waitPoint = BigDecimal.ZERO;
    this.gameWaitPoint = BigDecimal.ZERO;
    this.profitPoint = BigDecimal.ZERO;
    this.totalFeePoint = BigDecimal.ZERO;
    this.totalTradeMoney = BigDecimal.ZERO;
    this.totalDepositMoney = BigDecimal.ZERO;
    this.totalWithdrawMoney = BigDecimal.ZERO;
    this.address = address;
    this.coinName = coinName;
    this.precision = 8;
    this.currency = type.getDesc();

  }

  @PrePersist
  private void onCreate() {
    this.createDate = new Date();
  }

  @JsonIgnore
  public BigDecimal getRealPoint() {
    BigDecimal realPoint = this.point;
    if (this.pnl != null) {
      realPoint = realPoint.add(this.pnl);
    }
    return realPoint;
  }
}
