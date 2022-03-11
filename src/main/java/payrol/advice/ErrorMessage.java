package payrol.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
  private int statusCode;

  private Date timestamp;

  private String message;

  private String description;
}
