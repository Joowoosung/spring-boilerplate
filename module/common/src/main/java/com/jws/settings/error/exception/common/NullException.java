package com.jws.settings.error.exception.common;


import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class NullException extends BusinessException {

  public NullException(String message) {
    super(message, ErrorCode.NULL);
  }

  public NullException() {
    super(ErrorCode.NULL);
  }
}
