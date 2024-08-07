package za.co.shabisa.security.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import za.co.shabisa.domain.model.CookieInformation;

@Slf4j
public class SessionManager {
  private static final String SESSION_COOKIE_NAME = "sAID";
  private static final String REFRESH_COOKIE_NAME = "rAID";

  public static Optional<CookieInformation> getSessionTokenInformation(HttpServletRequest request) {
    return getCookieInformation(request, SESSION_COOKIE_NAME);
  }

  public static Optional<CookieInformation> getRefreshTokenInformation(HttpServletRequest request) {
    return getCookieInformation(request, REFRESH_COOKIE_NAME);
  }

  private static Optional<CookieInformation> getCookieInformation(HttpServletRequest request, String cookieName) {
    List<CookieInformation> cookies = cookieInformation(request.getCookies());
    return cookies.stream().filter(cookie -> cookieName.equalsIgnoreCase(cookie.getName())).findFirst();
  }

  private static List<CookieInformation> cookieInformation(Cookie[] cookies) {
    List<CookieInformation> cookiesInformation = new ArrayList<>();

    if (cookies == null || cookies.length == 0) {
      return cookiesInformation;
    }
    
    for (Cookie cookie : cookies) {
      log.debug("Cookie info: [name: {}, value: {}]", cookie.getName(), cookie.getValue());
      CookieInformation information = CookieInformation.builder()
          .name(cookie.getName())
          .domain(cookie.getDomain())
          .value(cookie.getValue())
          .maxAge(cookie.getMaxAge())
          .path(cookie.getPath())
          .secure(cookie.getSecure())
          .build();
      cookiesInformation.add(information);
    }
    
    return cookiesInformation;
  }

  public static void setRefreshToken(HttpServletResponse response, String value) {
  }

  public static void setSessionToken(HttpServletResponse response, String newSessionToken) {
  }
}
