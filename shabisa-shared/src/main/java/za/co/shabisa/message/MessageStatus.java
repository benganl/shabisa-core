package za.co.shabisa.message;

import java.io.Serializable;

public enum MessageStatus implements Serializable {
  ERROR, SUCCESS, FAILURE, REJECTED, WARNING, VALIDATION_FAILURE, NOT_FOUND;
}
