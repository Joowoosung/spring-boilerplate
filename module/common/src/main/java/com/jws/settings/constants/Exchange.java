package com.jws.settings.constants;

import java.util.HashMap;
import java.util.Map;

public class Exchange {

  public static final String KIWOOM = "KIWOOM";
  public static final String KIWOOM_GBPAUD = "KIWOOM_GBPAUD";
  public static final String KIWOOM_EURUSD = "KIWOOM_EURUSD";
  public static final String KIWOOM_NASDAQ = "KIWOOM_NASDAQ";
  public static final String KIWOOM_SNP500 = "KIWOOM_SNP500";
  //  public static final String LIVERATES = "LIVERATES";
  public static final String OANDA_EURUSD = "OANDA_EURUSD";
  public static final String OANDA_GBPAUD = "OANDA_GBPAUD";
  public static final String OANDA_XAUUSD = "OANDA_XAUUSD";

  public static final String BINANCE_BTCUSDT = "BINANCE_BTCUSDT";
  public static final String BINANCE_BTCUSDTMARK = "BINANCE_BTCUSDTMARK";
  public static final String BINANCE_BTCUSDTPERP = "BINANCE_BTCUSDTPERP";
  public static final String BINANCE_BTCUSDTPERP_ORDERBOOK = "BINANCE_BTCUSDTPERP_ORDERBOOK";
  public static final String BINANCE_BTCUSDTPERP_OHLC = "BINANCE_BTCUSDTPERP_OHLC";

  public static final String BINANCE_ETHUSDT = "BINANCE_ETHUSDT";
  public static final String BINANCE_ETHUSDTMARK = "BINANCE_ETHUSDTMARK";
  public static final String BINANCE_ETHUSDTPERP = "BINANCE_ETHUSDTPERP";
  public static final String BINANCE_ETHUSDTPERP_ORDERBOOK = "BINANCE_ETHUSDTPERP_ORDERBOOK";
  public static final String BINANCE_ETHUSDTPERP_OHLC = "BINANCE_ETHUSDTPERP_OHLC";

  public static final String BINANCE_DOGEUSDT = "BINANCE_DOGEUSDT";
  public static final String BINANCE_DOGEUSDTMARK = "BINANCE_DOGEUSDTMARK";
  public static final String BINANCE_DOGEUSDTPERP = "BINANCE_DOGEUSDTPERP";
  public static final String BINANCE_DOGEUSDTPERP_ORDERBOOK = "BINANCE_DOGEUSDTPERP_ORDERBOOK";
  public static final String BINANCE_DOGEUSDTPERP_OHLC = "BINANCE_DOGEUSDTPERP_OHLC";

  public static final String BINANCE_XRPUSDTMARK = "BINANCE_XRPUSDTMARK";
  public static final String BINANCE_XRPUSDTPERP = "BINANCE_XRPUSDTPERP";
  public static final String BINANCE_XRPUSDTPERP_ORDERBOOK = "BINANCE_XRPUSDTPERP_ORDERBOOK";
  public static final String BINANCE_XRPUSDTPERP_OHLC = "BINANCE_XRPUSDTPERP_OHLC";

  public static final String BINANCE_EOSUSDTMARK = "BINANCE_EOSUSDTMARK";
  public static final String BINANCE_EOSUSDTPERP = "BINANCE_EOSUSDTPERP";
  public static final String BINANCE_EOSUSDTPERP_ORDERBOOK = "BINANCE_EOSUSDTPERP_ORDERBOOK";
  public static final String BINANCE_EOSUSDTPERP_OHLC = "BINANCE_EOSUSDTPERP_OHLC";


  public static final Map<String, String> STOCK_KO = new HashMap<String, String>() {{
    put("BTCUSDT", "비트코인");
    put("ETHUSDT", "이더리움");
    put("DOGEUSDT", "도지코인");
    put("XRPUSDT", "리플");
    put("EOSUSDT", "이오스");
    put("LTCUSDT", "라이트코인");
    put("SOLUSDT", "솔라나");
    put("TRXUSDT", "트론");
    put("DOTUSDT", "폴카닷");
    put("ADAUSDT", "에이다");
    put("LINKUSDT", "체인링크");
    put("ATOMUSDT", "코스모스");

    put("GBPAUD", "GBPAUD");
    put("XAUUSD", "골드");
  }};
}
