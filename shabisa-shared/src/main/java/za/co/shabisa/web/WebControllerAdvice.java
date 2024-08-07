package za.co.shabisa.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import za.co.shabisa.exception.ShabisaException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = WebController.class)
public class WebControllerAdvice {

  @ExceptionHandler(ShabisaException.class)
  public String handleShabisaException(HttpServletRequest request, ShabisaException ex, Model model) {
    log.error(ex.getMessage(), ex);
    model.addAttribute("message", ex.getMessage());
    model.addAttribute("errors", ex.getErrors());
    return "error";
  }

  @ExceptionHandler(Exception.class)
  public String handleException(HttpServletRequest request, Exception ex, Model model) {
    log.error(ex.getMessage(), ex);
    model.addAttribute("message", ex.getMessage());
    return "error";
  }
}
