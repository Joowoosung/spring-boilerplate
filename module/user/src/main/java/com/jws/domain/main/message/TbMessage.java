package com.jws.domain.main.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jws.domain.main.user.Player;
import com.querydsl.core.annotations.QueryInit;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "message", indexes = {
    @Index(name = "IDX_DATE", columnList = "createDate")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class TbMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  @QueryInit({"user"})
  @Getter(onMethod_ = @JsonIgnore)
  @Setter(onMethod_ = @JsonProperty)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player_id", referencedColumnName = "id")
  private Player player;

  @CreationTimestamp
  private Date createDate;

  @Column(columnDefinition = "CHAR(1)")
  private String isRead = "0";

  @Column(columnDefinition = "CHAR(1)")
  private String isAuto = "0"; // 자동생성 여부 (0: 수동생성, 1: 자동생성)

  @Builder
  public TbMessage(Long id, String content, Player player, String isRead, String isAuto) {
    this.id = id;
    this.content = content;
    this.player = player;
    this.isRead = isRead;
    this.isAuto = isAuto;
  }
}
