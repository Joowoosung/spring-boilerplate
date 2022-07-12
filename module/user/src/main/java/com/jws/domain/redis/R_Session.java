package com.jws.domain.redis;


import java.io.Serializable;

import com.jws.domain.main.dto.LoginAuth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "ws_session")
@ToString
@NoArgsConstructor
public class R_Session implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String sessionId;
  @Indexed
  private String principal;
  @Indexed
  private Long siteInfoId;
//  @TimeToLive
//  private Long expireSecond;


  public R_Session(String id, LoginAuth auth) {
    this.sessionId = id;
    if (auth != null) {
      this.principal = auth.getPrincipal();
    } else {
      this.principal = "-";
    }
//    this.expireSecond = 10L;
  }

}
