package com.jws.settings.error.exception.common;


import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class InvalidValueException extends BusinessException {

  public InvalidValueException(String value) {
    super(value, ErrorCode.INVALID_INPUT_VALUE);
  }

  public InvalidValueException(String value, ErrorCode errorCode) {
    super(value, errorCode);
  }
}
