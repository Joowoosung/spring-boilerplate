package com.jws.settings.error.exception.common;


import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class CurrencyMismatchException extends BusinessException {

  public CurrencyMismatchException(String message) {
    super(message, ErrorCode.CURRENCY_MISMATCH);
  }

  public CurrencyMismatchException() {
    super(ErrorCode.CURRENCY_MISMATCH);
  }
}
