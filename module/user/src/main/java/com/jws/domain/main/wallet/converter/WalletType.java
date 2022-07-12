package com.jws.domain.main.wallet.converter;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum WalletType {
  BINARY("BINARY", "B"),
  FUTURES("FUTURES", "F"),
  BTC("BTC", "1"),
  ETH("ETH", "2"),
  XRP("XRP", "3"),
  DOGE("DOGE", "4"),
  USDT("USDT", "5");

  private String desc;
  private String code;

  WalletType(String desc, String code) {
    this.desc = desc;
    this.code = code;
  }

  public static WalletType parse(String code) {
    return Arrays.stream(WalletType.values())
        .filter(v -> v.getCode().equals(code))
        .findAny()
        .orElse(null);
    // FIXME: orElseThrow String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code);
  }

  public static WalletType parseDesc(String desc) {
    return Arrays.stream(WalletType.values())
        .filter(v -> v.getDesc().equals(desc))
        .findAny()
        .orElse(null);
    // FIXME: orElseThrow String.format("상태코드에 code=[%s]가 존재하지 않습니다.", code);
  }

  public static boolean contains(WalletType type, WalletType... walletTypes) {
    for (WalletType walletType : walletTypes) {
      if(type.equals(walletType)) return true;
    }
    return false;
  }
}
