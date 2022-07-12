package com.jws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SimpleDto {

  @Getter
  @NoArgsConstructor
  public static class IdVal {

    private Long id;
    private String value; // Y, N

    public IdVal(Long id) {
      this.id = id;
    }

    public IdVal(String value) {
      this.value = value;
    }

    @Builder
    public IdVal(Long id, String value) {
      this.id = id;
      this.value = value;
    }
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class IdObj {

    private Long id;
    private Object value;
  }
}
