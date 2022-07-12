package com.jws.settings.error;

import com.jws.settings.error.exception.BusinessException;
import java.util.Collection;
import java.util.Map;

/**
 * 메소드 명의 반대되는 조건문이면 터진다.
 */
public final class UkAssert {

  private static final String IS_FALSE = "is false";

  private static final String IS_TRUE = "is true";

  private static final String IS_EMPTY = "should not be empty";

  private static final String IS_NULL = "should not be null";

  private static final String HAS_NO_TEXT = "should have text";

  private UkAssert() {
  }

  public static <T> T notNull(T object, BusinessException exception) {
    return assertThat(object != null, exception, IS_NULL, object);
  }

  public static <T> T[] notEmpty(T[] objects, BusinessException exception) {
    return assertThat(objects != null && objects.length > 0, exception,
        IS_EMPTY, objects);
  }

  public static <M extends Map<?, ?>> M notEmpty(M map, BusinessException exception) {
    return assertThat(!map.isEmpty(), exception, IS_EMPTY, map);
  }

  public static <C extends Collection<?>> C notEmpty(C col, BusinessException exception) {
    return assertThat(!col.isEmpty(), exception, IS_EMPTY, col);
  }

  // 조건문이 False일경우 Exception 발생
  public static boolean isTrue(boolean condition, BusinessException exception) {
    return assertThat(condition, exception, IS_TRUE, condition);
  }

  // 조건문이 True일경우 Exception 발생
  public static boolean isFalse(boolean condition, BusinessException exception) {
    return assertThat(!condition, exception, IS_FALSE, condition);
  }

  public static <T> T assertThat(boolean condition, BusinessException exception, String msgSuffix,
      T rv) {
    if (!condition) {
      throw exception;
    }
    return rv;
  }

}
