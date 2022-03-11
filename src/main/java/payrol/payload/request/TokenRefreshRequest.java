package payrol.payload.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {
  @NonNull
  private String refreshToken;
}
