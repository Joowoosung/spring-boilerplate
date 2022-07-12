package com.jws.dto.converter;

import com.google.gson.Gson;
import java.lang.reflect.ParameterizedType;
import javax.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseJsonConverter<T> implements AttributeConverter<T, String> {

  private final Gson gson = new Gson();

  @SuppressWarnings("unchecked")
  private Class<T> getGenericTypeClass() {
    try {
      String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
          .getTypeName();
      Class<?> clazz = Class.forName(className);
      return (Class<T>) clazz;
    } catch (Exception e) {
      throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
    }
  }

  @Override
  public String convertToDatabaseColumn(T attribute) {
    if (attribute != null) return gson.toJson(attribute);
    else return null;
  }

  @Override
  public T convertToEntityAttribute(String dbData) {
    return gson.fromJson(dbData, getGenericTypeClass());
  }
}
