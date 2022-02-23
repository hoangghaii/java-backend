package payrol.common;

public class NotFoundException extends RuntimeException {
  public NotFoundException(Long id, ErrorType errorType) {
    super("Could not find " + errorType + " " + id);
  }
}
