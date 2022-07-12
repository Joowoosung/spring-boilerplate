package com.jws.settings.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

  // Common
  INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
  METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
  ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
  INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
  INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
  HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
  NULL(400, "C007", "값이 Null입니다"),
  ETC(400, "C008", "정의되지 않은 로직 에러 발생"),
  CURRENCY_MISMATCH(400, "D001", "환율이 불일치"),

  // FILE: F
  FILE_STORAGE(500, "F001", "저장소가 없습니다."),
  FILE_NOT_FOUND(404, "F002", "일치하는 파일이 없습니다."),

  // ErrorCode The END
  ;


  private final String code;
  private final String message;
  private final int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCode() {
    return code;
  }

  public int getStatus() {
    return status;
  }

}
