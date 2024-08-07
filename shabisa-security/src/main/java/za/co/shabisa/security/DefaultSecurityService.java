package za.co.shabisa.security;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import za.co.shabisa.domain.model.CookieInformation;
import za.co.shabisa.domain.model.LoginInformation;
import za.co.shabisa.domain.model.UserInformation;
import za.co.shabisa.security.domain.service.UserService;
import za.co.shabisa.service.SecurityService;

@Slf4j
@Service
class DefaultSecurityService implements SecurityService {
  
  private final UserService userService;
  
  public DefaultSecurityService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void registerUser(UserInformation user) {
    log.info("Validate user information");
    
    log.info("Check if user exists");
    
    log.info("Register user");
  }

  @Override
  public CookieInformation authenticateUser(LoginInformation login) {
    log.info("Validate login information");
    
    log.info("");
    
    log.info("Generate refresh token");
    return null;
  }

  @Override
  public void requestPasswordReset(String email) {
  }

  @Override
  public void resetPassword(String token, String newPassword) {
  }

  @Override
  public void activateAccount(String token) {
  }

  @Override
  public void logout(HttpServletRequest request) {
  }

  @Override
  public String generateSessionToken(CookieInformation newRefresh) {
    return null;
  }

  @Override
  public String refreshSessionToken(String refreshToken) {
    return null;
  }

  @Override
  public void extendSession(String sessionToken) {
  }
}
