package za.co.shabisa.security.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.shabisa.rest.ResourceController;
import za.co.shabisa.service.SecurityService;

@ResourceController
@RequestMapping(path = "/auth", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class SecurityResource {

  private final SecurityService securityService;

  SecurityResource(SecurityService securityService) {
    this.securityService = securityService;
  }

  @GetMapping()
  public String ping() {
    return "hello from Shabisa Core...";
  }
}
