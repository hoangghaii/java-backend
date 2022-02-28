package payrol.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  private String description;

  @NotBlank
  private EStatus status;

  public Order() {}

  public Order(String description, EStatus status) {
    this.description = description;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EStatus getStatus() {
    return status;
  }

  public void setStatus(EStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Order)) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id) &&
        Objects.equals(description, order.description) &&
        Objects.equals(status, order.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, status);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", status=" + status +
        '}';
  }
}
