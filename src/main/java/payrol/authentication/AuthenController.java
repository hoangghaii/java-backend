package payrol.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import payrol.Employee.*;

@RestController
public class AuthenController {

  private final EmployeeModelAssembler assembler;

  AuthenController(EmployeeModelAssembler assembler) {
    this.assembler = assembler;
  }

  @Autowired
  private EmployeeRepository repository;

  @PostMapping("/register")
  ResponseEntity<?> register(@RequestBody Employee employee) {
    EntityModel<Employee> entityModel = assembler.toModel(repository.save(employee));

    return ResponseEntity.status(HttpStatus.CREATED).body("Create successfully");
  }
}
