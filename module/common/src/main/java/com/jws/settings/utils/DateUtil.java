package com.jws.settings.utils;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil implements Serializable {

  public static final DateTimeFormatter dateTimeAllFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
  public static final ZoneId defaultZoneId = ZoneId.of("Asia/Seoul");
  public static final ZoneOffset defaultZoneOffset = ZoneOffset.of("+09:00");
  private static final long serialVersionUID = 1L;
}
