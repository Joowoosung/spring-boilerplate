package com.jws.settings.error.exception.common;


import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class FileException extends BusinessException {

  public FileException(String message, ErrorCode errorCode) {
    super(message, errorCode);
  }
}
