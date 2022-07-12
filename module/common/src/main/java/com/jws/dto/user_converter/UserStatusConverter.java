package com.jws.dto.user_converter;

import com.google.gson.Gson;
import javax.persistence.AttributeConverter;

public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

  private final Gson gson = new Gson();

  @Override
  public String convertToDatabaseColumn(UserStatus attribute) {
    if (attribute != null) return attribute.getCode();
    else return null;
  }

  @Override
  public UserStatus convertToEntityAttribute(String dbData) {
    return UserStatus.parse(dbData);
  }
}
