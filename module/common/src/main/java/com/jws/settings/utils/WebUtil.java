package com.jws.settings.utils;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebUtil {

  public static String getResponseString(HttpServletRequest request, int status, String error, String message) {
    JsonObject json = new JsonObject();

    json.addProperty("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    json.addProperty("status", status);
    json.addProperty("error", error);
    json.addProperty("message", message);
    json.addProperty("path", request.getRequestURI());
    return json.toString();
  }

  public static String getConnectIp(HttpServletRequest request) {
    String connectIp = request.getHeader("CF-Connecting-IP");
    if (StringUtils.isEmpty(connectIp)) connectIp = request.getHeader("X-FORWARDED-FOR");
    if (StringUtils.isEmpty(connectIp)) connectIp = request.getRemoteAddr();
    return connectIp;
  }
}
