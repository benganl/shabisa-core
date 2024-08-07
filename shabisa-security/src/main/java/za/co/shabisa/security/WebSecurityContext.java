package za.co.shabisa.security;

import java.io.IOException;
import java.util.Optional;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import za.co.shabisa.domain.model.CookieInformation;
import za.co.shabisa.domain.model.LoginInformation;
import za.co.shabisa.security.session.SessionManager;
import za.co.shabisa.service.SecurityService;

@Slf4j
public class WebSecurityContext implements SecurityContext {

  private final SecurityService securityService;
  private final HttpServletRequest request;
  private final HttpServletResponse response;
  private final LoginInformation login;

  public WebSecurityContext(SecurityService securityService, HttpServletRequest request,
      HttpServletResponse response, LoginInformation login) {
    this.securityService = securityService;
    this.request = request;
    this.response = response;
    this.login = login;
  }

  @Override
  public void signInUser() {
    final Optional<CookieInformation> sessionCookie = SessionManager.getSessionTokenInformation(request);
    final Optional<CookieInformation> refreshCookie = SessionManager.getRefreshTokenInformation(request);

    boolean hasSession = sessionCookie.isPresent();
    boolean hasRefresh = refreshCookie.isPresent();

    try {
      if (!hasSession && !hasRefresh) {
        // Scenario 1: New authentication, create new tokens
        log.info("New authentication. Creating a refresh token and a session token.");
        CookieInformation newRefresh = securityService.authenticateUser(login);
        String newSessionToken = securityService.generateSessionToken(newRefresh);
        SessionManager.setRefreshToken(response, newRefresh.getValue());
        SessionManager.setSessionToken(response, newSessionToken);
      } else if (hasSession && !hasRefresh) {
        // Scenario 2: Inconsistent state - session without refresh token
        log.error("Inconsistent state: session token present without a refresh token.");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session token without refresh token.");
      } else if (!hasSession && hasRefresh) {
        // Scenario 3: Session expired, refresh token present
        log.info("Session expired. Refreshing session token.");
        String refreshToken = refreshCookie.get().getValue();
        String newSessionToken = securityService.refreshSessionToken(refreshToken);
        SessionManager.setSessionToken(response, newSessionToken);
      } else if (hasSession && hasRefresh) {
        // Scenario 4: Both tokens present, extend session duration
        log.info("Both tokens present. Extending session duration.");
        String sessionToken = sessionCookie.get().getValue();
        securityService.extendSession(sessionToken);
        // Re-set session cookie with extended duration
        SessionManager.setSessionToken(response, sessionToken);
      }
    } catch (Exception e) {
      log.error("Error during user sign-in", e);
      try {
        response.sendRedirect("/sign-in?error=" + e.getMessage());
      } catch (IOException ioException) {
        log.error("Error redirecting to sign-in page", ioException);
      }
    }
  }
}
