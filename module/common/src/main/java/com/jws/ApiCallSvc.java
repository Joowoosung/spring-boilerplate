package com.jws;

import com.jws.dto.SmsDto;
import com.jws.settings.utils.GlobalGson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiCallSvc {

  @Value("${config.intranet.tps_server_url:}")
  private String TPS_SERVER_URL;
  @Value("${config.intranet.tps_api_key:}")
  private String TPS_API_KEY;
  @Value("${config.sms_phone_num:}")
  private String SMS_PHONE_NUM;

  public void sendNologSmsForTPS(String receiver, String message, String customSender) {
    String sender = StringUtils.isEmpty(customSender) ? SMS_PHONE_NUM : customSender;
    try {
      URL url = new URL(TPS_SERVER_URL + "/api/sms/nolog_send");
      SmsDto smsDto = SmsDto.builder()
          .receiver(receiver).message(message)
          .name("COOLSMS").sender(sender)
          .apiKey("NCSXUQ9FLKHQB844").apiSecret("Y95CRP2EH9JKT9QAZ08J4KTODGSWRQUT")
          .build();

      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("User-Agent",
          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);
      conn.setRequestProperty("Authorization", TPS_API_KEY);

      OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
      wr.write(GlobalGson.get().toJson(smsDto));
      wr.flush();
      wr.close();

      int responseCode = conn.getResponseCode();
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
