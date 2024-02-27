package server;

import com.sun.net.httpserver.HttpExchange;
import utils.FileUtil;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CookieServer<V> {
  private final String name;
  private final V value;
  private Integer maxAge;
  private boolean httpOnly;

  public CookieServer(String name, V value) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(value);
    this.name = name.strip();
    this.value = value;
  }
  
  public static <V> CookieServer make(String name, V value) {
    return new CookieServer<>(name, value);
  }

  public void setMaxAge(Integer maxAgeInSeconds) {
    this.maxAge = maxAgeInSeconds;
  }

  public void setHttpOnly(boolean httpOnly) {
    this.httpOnly = httpOnly;
  }

  private V getValue() { return value; }
  private Integer getMaxAge() { return maxAge; }
  private String getName() { return name; }
  private boolean isHttpOnly() { return httpOnly; }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    Charset utf8 = StandardCharsets.UTF_8;
    String encName = URLEncoder.encode(getName().strip(), utf8);
    String valueStr = getValue().toString();
    String encValue = URLEncoder.encode(valueStr, utf8);

    sb.append(String.format("%s=%s", encName, encValue));
    if (getMaxAge() != null){
      sb.append(String.format("; Max-Age=%s", getMaxAge()));
    }

    if(isHttpOnly()){
      sb.append("; HttpOnly");
    }

    return sb.toString();
  }

  public static Map<String, String> parse(String cookieString) {
    return FileUtil.parseUrlEncoded(cookieString, ";");
  }
}