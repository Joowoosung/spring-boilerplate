package com.jws.settings.error.exception.common;

import com.jws.settings.error.ErrorCode;
import com.jws.settings.error.exception.BusinessException;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException() {
    super(ErrorCode.ENTITY_NOT_FOUND);
  }

  public EntityNotFoundException(String message) {
    super(message, ErrorCode.ENTITY_NOT_FOUND);
  }

  public EntityNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
