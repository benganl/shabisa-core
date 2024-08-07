package za.co.shabisa.service;

import jakarta.servlet.http.HttpServletRequest;
import za.co.shabisa.domain.model.CookieInformation;
import za.co.shabisa.domain.model.LoginInformation;
import za.co.shabisa.domain.model.UserInformation;

public interface SecurityService {

  void registerUser(UserInformation user);

  CookieInformation authenticateUser(LoginInformation login);

  void requestPasswordReset(String email);

  void resetPassword(String token, String newPassword);

  void activateAccount(String token);

  void logout(HttpServletRequest request);

  String generateSessionToken(CookieInformation newRefresh);

  String refreshSessionToken(String refreshToken);

  void extendSession(String sessionToken);

}
