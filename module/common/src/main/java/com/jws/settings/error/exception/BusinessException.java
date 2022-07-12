package com.jws.settings.error.exception;


import com.jws.settings.error.ErrorCode;

public class BusinessException extends RuntimeException {

  private ErrorCode errorCode;
  private boolean isCustom = false;

  public BusinessException(String message, ErrorCode errorCode) {
    super(message);
    this.isCustom = true;
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public boolean isCustom() {
    return isCustom;
  }
}
