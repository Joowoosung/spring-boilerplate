package com.jws.settings.constants;

import java.io.Serializable;

public class PointType implements Serializable {

  public static final String GAME_WIN = "1"; // 실현
  public static final String GAME_LOSE = "2"; // 실격
  public static final String GAME_DRAW = "3"; // 무효
  public static final String GAME_BET = "4"; // 배팅
  public static final String DEPOSIT = "D"; // 입금
  public static final String WITHDRAW = "W"; // 출금승인
  public static final String FORCE_DEPOSIT = "FD"; // 강제입금
  public static final String FORCE_WITHDRAW = "FW"; // 강제출금
  public static final String FORCE_WALLET_BUY = "FB"; // 강제출금
  public static final String FORCE_WALLET_SELL = "FS"; // 강제출금

  public static final String BITDEX_SEND = "BS";
  public static final String BITSHOP_SEND = "B2";

  public static final String WALLET_BUY = "WB"; // 구매
  public static final String WALLET_SELL = "WS"; // 판매
  public static final String WALLET_SELL_APPLY = "SA"; // 판매
  public static final String BONUS = "B"; // 보너스
  //  public static final String CALC = "C"; // 정산승인
//  public static final String FORCE_CALC = "FC"; // 정산승인
//  public static final String FORCE_PARNTER_PLUS = "FP"; // 지급
//  public static final String FORCE_PARNTER_MINUS = "FM"; // 차감
  public static final String FORCE_PLAYER_MINUS = "PM"; // 차감
//  public static final String CALC_WITHDRAW = "CW"; // 출금정산 동시
  public static final String PARTNER_BONUS = "PB"; // 파트너가 플레이어에게 보너스지급
  public static final String WITHDRAW_APPLY = "WA"; // 출금요청
  public static final String WITHDRAW_REJECT = "WR"; // 출금거절
  public static final String WITHDRAW_CANCEL = "WC"; // 출금취소
  public static final String PARTNER_PROFIT = "PF"; // 수수료
//  public static final String CALC_APPLY = "CA";
//  public static final String CALC_REJECT = "CR"; // 출금거절
//  public static final String CALC_CANCEL = "CC"; // 출금취소
  public static final String GAME_FEE = "GF";
  public static final String DNW_FEE = "DF";
  public static final String ROLE_BACK = "RB";
  public static final String TRANSFER_IN = "TI";
  public static final String TRANSFER_OUT = "TO";
  public static final String CONVERT_IN = "CI";
  public static final String CONVERT_OUT = "CO";

  public static final String POSITION_OPEN = "PO";
  public static final String POSITION_CLOSE = "PC";
  public static final String MAKE_ORDER = "MO";
  public static final String ORDER_CANCEL = "OC"; // 컨버트랑 헷갈려서 요래씀
  public static final String POSITION_LIQUID = "PL";
  public static final String POSITION_CROSSLIQUID = "CL";
  public static final String POSITION_CLOSEOPEN = "PP";
  public static final String PROFIT = "P";
  public static final String TEMP_WALLET = "TW";
  public static final String MARGIN_CHANGE = "MC";
  public static final String FUNDING = "F";
  public static final String FUNDING_CROSS = "FC";
  public static final String UPDATE_BONUS = "UB";

  public static final String COPYTRADE_PNL = "CP";
  public static final String COPYTRADE_TRADER_FEE = "CF";
  private static final long serialVersionUID = 1L;
//  public static final String COUPON = "CP";
}
