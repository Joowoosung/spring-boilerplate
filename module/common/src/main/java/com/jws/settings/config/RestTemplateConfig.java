package com.jws.settings.config;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  @Bean
  public RestTemplate exchangeRestTemp() {
    RestTemplateBuilder builder = new RestTemplateBuilder();
    builder.setReadTimeout(Duration.ofSeconds(5));
    builder.setConnectTimeout(Duration.ofSeconds(5));

    RestTemplate rest = builder.build();
    rest.setInterceptors(Collections.singletonList((req, body, execution) -> {
      HttpHeaders headers = req.getHeaders();
      headers.set(HttpHeaders.CONNECTION, "Keep-Alive");
      headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
      headers.set(HttpHeaders.USER_AGENT,
          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      return execution.execute(req, body);
    }));
    rest.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public boolean hasError(ClientHttpResponse response) {
        return false;
      }

      @Override
      public void handleError(ClientHttpResponse response) throws IOException {

      }
    });
    return rest;
  }

}
