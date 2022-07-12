package com.jws.ctrl.rest;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile({"test", "local"})
@RequestMapping(value = "/test_api")
@RestController
@RequiredArgsConstructor
public class TestCtrl {


  @PostMapping("/logtest")
  public ResponseEntity logtest(@RequestBody Map<String, String> map) {
    return ResponseEntity.ok(null);
  }
}
