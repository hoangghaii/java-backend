package payrol.payload.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class JwtResponse {
  private String token;

  @Value("${demo.app.tokenPrefix}")
  private String type;

  private String refreshToken;

  private Long id;

  private String username;

  private String email;

  private List<String> roles;

  public String getToken() {
    return token;
  }

  public JwtResponse(
      String token,
      String refreshToken,
      Long id,
      String username,
      String email,
      List<String> roles
  ) {
    this.token = token;
    this.refreshToken = refreshToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}