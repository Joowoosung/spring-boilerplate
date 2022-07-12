package com.jws.settings.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GlobalGson {

  private static final GlobalGson globalGson = new GlobalGson();
  private Gson gson = new Gson();

  private GlobalGson() {

  }

  public static GlobalGson get() {
    return globalGson;
  }

  public Gson getGson() {
    if (gson == null) {
      gson = new Gson();
    }
    return gson;
  }

  public <T> String toJson(final T t) {
    return getGson().toJson(t);
  }

  public <T> JsonObject getAsJsonObject(final T t) {
    return getGson().toJsonTree(t).getAsJsonObject();
  }

  public <T> T fromJson(final String o, Class<T> tClass) {
    return getGson().fromJson(o, tClass);
  }

  public <T> T fromJson(final String json, Type typeOfT) {
    return getGson().fromJson(json, typeOfT);
  }

  public <T> T fromJson(final JsonElement o, Class<T> tClass) {
    return getGson().fromJson(o, tClass);
  }

  public <E> ArrayList<E> listFromJson(final String o, final Type listType) {
    return getGson().fromJson(o, listType);
  }

  public <E> ArrayList<E> listFromJson(final JsonElement o, final Type listType) {
    return getGson().fromJson(o, listType);
  }

  public <E> ArrayList<E> listFromJson(final String o, final Class<E> eClass) {
    return getGson().fromJson(o, new ListType<>(eClass));
  }

  public <E> ArrayList<E> listFromJson(final JsonElement o, final Class<E> eClass) {
    return getGson().fromJson(o, new ListType<>(eClass));
  }

  private static class ListType<E> implements ParameterizedType {

    private Class<?> wrapped;

    private ListType(Class<E> wrapped) {
      this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
      return new Type[]{wrapped};
    }

    public Type getRawType() {
      return ArrayList.class;
    }

    public Type getOwnerType() {
      return null;
    }
  }
}
