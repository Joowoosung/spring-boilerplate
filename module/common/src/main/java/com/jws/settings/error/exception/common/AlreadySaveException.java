package com.jws.settings.error.exception.common;


import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class AlreadySaveException extends BusinessException {

  public AlreadySaveException(ErrorCode errorCode) {
    super(errorCode);
  }

  public AlreadySaveException(String value) {
    super(value, ErrorCode.INVALID_INPUT_VALUE);
  }

  public AlreadySaveException(String value, ErrorCode errorCode) {
    super(value, errorCode);
  }
}
