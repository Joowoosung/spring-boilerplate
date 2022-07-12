package com.jws.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class WsDto implements Serializable {

  private static final long serialVersionUID = 1L;

  //  @Getter(onMethod_ = @JsonIgnore)
//  @Setter(onMethod_ = @JsonProperty)
  private String path;
  private String type;
  private Object data;

  @Setter
  private String principal;
//

  @Builder
  public WsDto(String path, String type, Object data, String principal) {
    this.path = path;
    this.type = type;
    this.data = data;
    this.principal = principal;
  }

  public WsDto(String path, String type, Object data) {
    this.path = path;
    this.type = type;
    this.data = data;
  }

  public WsDto(String path, Object data) {
    this.path = path;
    this.data = data;
  }


}
