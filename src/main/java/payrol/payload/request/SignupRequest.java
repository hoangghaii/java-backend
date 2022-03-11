package payrol.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
  @NonNull
  @Size(min = 3, max = 20)
  private String username;

  @NonNull
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NonNull
  @Size(min = 6, max = 40)
  private String password;
}
