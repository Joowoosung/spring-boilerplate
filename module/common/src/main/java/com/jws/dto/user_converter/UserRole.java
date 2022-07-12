package com.jws.dto.user_converter;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum UserRole {
  READ_ADMIN("READ_ADMIN", "RA"),
  ADMIN("ADMIN", "A"),
  PARTNER("PARTNER", "PTR"),
  PLAYER("PLAYER", "P");

  private String desc;
  private String code;

  UserRole(String desc, String code) {
    this.desc = desc;
    this.code = code;
  }

  public static UserRole parse(String code) {
    return Arrays.stream(UserRole.values())
        .filter(v -> v.getCode().equals(code))
        .findAny()
        .orElse(null);
    // FIXME: orElseThrow String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code);
  }
}
