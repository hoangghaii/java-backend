package payrol.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class TokenRefreshResponse {
  private String accessToken;

  private String refreshToken;

  @Value("${demo.app.tokenPrefix}")
  private String tokenType = "Bearer";

  public TokenRefreshResponse(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
