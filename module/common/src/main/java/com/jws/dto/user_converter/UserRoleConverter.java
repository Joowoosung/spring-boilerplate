package com.jws.dto.user_converter;

import com.google.gson.Gson;
import javax.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, String> {

  private final Gson gson = new Gson();

  @Override
  public String convertToDatabaseColumn(UserRole attribute) {
    if (attribute != null) return attribute.getCode();
    else return null;
  }

  @Override
  public UserRole convertToEntityAttribute(String dbData) {
    return UserRole.parse(dbData);
  }
}
