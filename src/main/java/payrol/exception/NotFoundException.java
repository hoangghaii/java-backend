package payrol.exception;


import payrol.models.EErrorType;

public class NotFoundException extends RuntimeException {
  public NotFoundException(Long id, EErrorType errorType) {
    super("Could not find " + errorType + " " + id);
  }
}
