package payrol.payload.request;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginRequest {
  @NonNull
  private String username;

  @NonNull
  private String password;
}
