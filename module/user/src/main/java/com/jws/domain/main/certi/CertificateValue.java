package com.jws.domain.main.certi;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "certificate_value")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertificateValue {

  @Id
  @Column(name = "certi_key", length = 200, nullable = false)
  private String key;   // 키값
  @Column(name = "value", length = 100, nullable = false)
  private String value; // 코드값
  @Column(name = "expired_date", nullable = false)
  private Date expiredDate;

  public CertificateValue(String key) {
    this.key = key;
  }

  @PrePersist
  private void onCreate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, 1);
    this.expiredDate = cal.getTime();
  }
}
