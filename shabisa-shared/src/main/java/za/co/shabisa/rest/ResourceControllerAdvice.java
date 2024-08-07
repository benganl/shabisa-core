package za.co.shabisa.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.co.shabisa.exception.ShabisaException;
import za.co.shabisa.message.MessageStatus;
import za.co.shabisa.message.ResponseMessage;

@RestControllerAdvice(annotations = {ResourceController.class})
public class ResourceControllerAdvice {

  @ExceptionHandler(ShabisaException.class)
  public ResponseEntity<ResponseMessage> handleShabisaException(ShabisaException e) {
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setMessage(e.getMessage());
    responseMessage.setErrors(e.getErrors());
    responseMessage.setStatus(MessageStatus.ERROR);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
