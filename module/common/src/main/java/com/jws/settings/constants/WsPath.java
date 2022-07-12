package com.jws.settings.constants;

import java.io.Serializable;

public class WsPath implements Serializable {

  public static final String TOPIC = "/topic/all";
  // 에러 처리
  public static final String TOPIC_POPUP = "/topic/popup";
  // 에러 처리
  public static final String TOPIC_NOTICE = "/topic/notice";
  public static final String QUEUE_MESSAGE = "/queue/message";
  // 에러 처리
  public static final String QUEUE_ERROR = "/queue/error";
  // 에러 처리
  public static final String QUEUE_LOG_ERROR = "/queue/log_error";
  // 에러 처리
  public static final String QUEUE_BET_ERROR = "/queue/bet_error";
  // 에러 처리
  public static final String QUEUE_FORCE_LOGOUT = "/queue/force_logout";
  // 사용자 포인트 업데이트
  public static final String USER_POINT = "/queue/point";
  // 옵션 거래 현황
  public static final String TRADE_PREFIX = "/topic/trade/";
  // 사용자 거래 관련
  public static final String USER_TRADE_PREFIX = "/queue/trade/";
  // 사용자 베팅 관련
  public static final String USER_TRADE_BET = "/queue/trade/bet";
  public static final String USER_DNW = "/queue/dnw";
  public static final String TOPIC_BETTING = "/topic/betting/";
  public static final String USER_NEW_MESSAGE = "/queue/new_message";
  public static final String USER_QUESTION_FINISH = "/queue/question_finish";
  public static final String USER_FUTURES = "/queue/futures";
  public static final String USER_CROSS = "/queue/cross";
  public static final String TOPIC_FUTURES = "/topic/futures";

  public static final String TOPIC_ADMIN_FUTURES = "/topic/admin/futures/";

  public static final String TOPIC_FUTURES_ORDER_SUCCESS = "/topic/order_success";
  public static final String TOPIC_FUTURES_ORDER_FAIL = "/topic/order_fail";

  private static final long serialVersionUID = 1L;
}
