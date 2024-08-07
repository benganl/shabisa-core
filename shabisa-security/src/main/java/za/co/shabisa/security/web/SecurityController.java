package za.co.shabisa.security.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import za.co.shabisa.domain.model.LoginInformation;
import za.co.shabisa.domain.model.UserInformation;
import za.co.shabisa.security.SecurityContext;
import za.co.shabisa.service.SecurityService;
import za.co.shabisa.web.WebController;

@WebController
public class SecurityController {

  private final SecurityService securityService;

  public SecurityController(SecurityService securityService) {
    this.securityService = securityService;
  }

  @GetMapping({"", "/"})
  public String index() {
    return "index";
  }

  @GetMapping("/sign-up")
  public String signUp(Model model) {
    model.addAttribute("user", new UserInformation());
    return "sign-up";
  }

  @PostMapping("/sign-up")
  public String signUpPost(@ModelAttribute UserInformation user, Model model) {
    try {
      securityService.registerUser(user);
      return "redirect:/sign-in";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "sign-up";
    }
  }

  @GetMapping("/sign-in")
  public String signIn(Model model) {
    model.addAttribute("login", new LoginInformation());
    return "sign-in";
  }

  @PostMapping("/sign-in")
  public String signInPost(@ModelAttribute LoginInformation login, Model model,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityContext securityContext = SecurityContext.create(securityService, request, response, login);
      securityContext.signInUser();
      return "redirect:/home";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "sign-in";
    }
  }

  @PostMapping("/password-reset/request")
  public String requestPasswordReset(@RequestParam String email, Model model) {
    try {
      securityService.requestPasswordReset(email);
      model.addAttribute("message", "Password reset instructions sent.");
      return "password-reset";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "password-reset";
    }
  }

  @PostMapping("/password-reset/confirm")
  public String confirmPasswordReset(@RequestParam String token, @RequestParam String newPassword,
      Model model) {
    try {
      securityService.resetPassword(token, newPassword);
      return "redirect:/sign-in";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "password-reset";
    }
  }

  @GetMapping("/sign-out")
  public String signOut(HttpServletRequest request) {
    securityService.logout(request);
    return "redirect:/security";
  }

  @PostMapping("/activate")
  public String activateProfile(@RequestParam String token, Model model) {
    try {
      securityService.activateAccount(token);
      return "redirect:/sign-in";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "activation-failed";
    }
  }
}
