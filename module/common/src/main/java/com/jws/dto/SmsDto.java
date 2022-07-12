package com.jws.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SmsDto {

  private String receiver;
  private String message;
  private Long providerId;
  private String name; // 서비스명 (coolsms, twillo 등등)
  private String sender; // 이메일이 될수도 있고 전화번호가 될수도 있고
  private String apiKey;
  private String apiSecret;

  @Builder
  public SmsDto(String receiver, String message, Long providerId, String name, String sender, String apiKey,
      String apiSecret) {
    this.receiver = receiver;
    this.message = message;
    this.providerId = providerId;
    this.name = name;
    this.sender = sender;
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }
}
