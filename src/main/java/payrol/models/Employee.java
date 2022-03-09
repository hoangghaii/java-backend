package payrol.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @GeneratedValue
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private String role;
}
