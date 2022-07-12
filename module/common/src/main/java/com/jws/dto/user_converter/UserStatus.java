package com.jws.dto.user_converter;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum UserStatus {
  SUCCESS("SUCCESS", "S"),
  DENY("DENY", "D"),
  LOADED("LOADED", "L"),
  WITHDRAW("WITHDRAW", "W");

  private String desc;
  private String code;

  UserStatus(String desc, String code) {
    this.desc = desc;
    this.code = code;
  }

  public static UserStatus parse(String value) {
    return Arrays.stream(UserStatus.values())
        .filter(v -> v.getCode().equals(value))
        .findAny()
        .orElse(null);
    // FIXME: orElseThrow String.format("상태코드에 value=[%s]가 존재하지 않습니다.", value);
  }
}
