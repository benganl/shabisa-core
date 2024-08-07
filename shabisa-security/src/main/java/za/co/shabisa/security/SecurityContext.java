package za.co.shabisa.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import za.co.shabisa.domain.model.LoginInformation;
import za.co.shabisa.service.SecurityService;

public interface SecurityContext {

  static SecurityContext create(SecurityService securityService, HttpServletRequest request,
      HttpServletResponse response, LoginInformation login) {
    return new WebSecurityContext(securityService, request, response, login);
  }

  void signInUser();
}
