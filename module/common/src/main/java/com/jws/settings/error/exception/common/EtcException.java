package com.jws.settings.error.exception.common;


import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class EtcException extends BusinessException {

  public EtcException(String message) {
    super(message, ErrorCode.ETC);
  }

  public EtcException() {
    super(ErrorCode.ETC);
  }
}
