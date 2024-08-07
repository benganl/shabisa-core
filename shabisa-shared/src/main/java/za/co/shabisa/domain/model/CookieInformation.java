package za.co.shabisa.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CookieInformation {
  private String name;
  private String domain;
  private String value;
  private int maxAge;
  private String path;
  private boolean secure;
}
