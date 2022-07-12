package com.jws.settings.constants;

public class FuturesType {

  public static final String POSITION_OPEN = "POSITION_OPEN"; // 포지션 열릴시 (알림 X)
  public static final String POSITION_UPDATE = "POSITION_UPDATE"; // 포지션 정보변경 (알림 X)
  public static final String POSITION_CLOSE = "POSITION_CLOSE"; // 포지션 종료 (알림 X)
  public static final String POSITION_WARN = "POSITION_WARN"; // 포지션 청산위험
  public static final String POSITION_LIQUID = "POSITION_LIQUID"; // 포지션 청산
  public static final String POSITION_UPDATE_LIQUIDPRICE = "POSITION_UPDATE_LIQUIDPRICE"; // 포지션 정보변경 (알림 X)


  public static final String ORDER_SUCCESS = "ORDER_SUCCESS"; // 주문 성공
  public static final String ORDER_CANCEL = "ORDER_CANCEL"; // 주문 취소
  public static final String ORDER_FILLED = "ORDER_FILLED"; // 주문 체결완료
  public static final String ORDER_UPDATE = "ORDER_UPDATE"; // 주문 업데이트 (알림 X - 스탑오더 체결시발생할때)

  public static final String COPYTRADE_OPEN = "COPYTRADE_OPEN"; // 카피트레이더 포지션 오픈
  public static final String COPYTRADE_CLOSE = "COPYTRADE_CLOSE"; // 카피트레이더 포지션 종료

  public static final String LEVERAGE_UPDATE = "LEVERAGE_UPDATE"; // 레버리지 변경
}
