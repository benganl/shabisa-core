package za.co.shabisa.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public abstract class Message implements Serializable {
  private static final long serialVersionUID = 1L;

  private String messageId;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Message message = (Message) o;
    return Objects.equals(messageId, message.messageId);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(messageId);
  }
}
