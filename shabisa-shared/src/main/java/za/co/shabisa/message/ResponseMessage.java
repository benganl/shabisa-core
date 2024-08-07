package za.co.shabisa.message;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseMessage extends Message {
  private static final long serialVersionUID = 1L;

  private String message;
  private MessageStatus status;
  private List<String> errors;
}
