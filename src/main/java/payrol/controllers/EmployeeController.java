package payrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payrol.assembler.EmployeeModelAssembler;
import payrol.exception.NotFoundException;
import payrol.models.EErrorType;
import payrol.repository.EmployeeRepository;
import payrol.models.Employee;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class EmployeeController {

  private final EmployeeModelAssembler assembler;

  EmployeeController(EmployeeModelAssembler assembler) {
    this.assembler = assembler;
  }

  @Autowired
  private EmployeeRepository repository;

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  public CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repository.findAll().stream()
        .map(assembler::toModel).collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class)
        .all()).withSelfRel());
  }

  @PostMapping("/employees")
  ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){
    EntityModel<Employee> entityModel = assembler.toModel(newEmployee);

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
  }

  // Single item
  @GetMapping("/employees/{id}")
  public EntityModel<Employee> one(@PathVariable Long id){
    Employee employee = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id, EErrorType.employee));

    return assembler.toModel(employee);
  }

  @PutMapping("/employees/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
    Employee updatedEmployee = repository.findById(id)
        .map(employee -> {
          employee.setFirstName(newEmployee.getFirstName());
          employee.setLastName(newEmployee.getLastName());
          employee.setRole(newEmployee.getRole());
          return repository.save(employee);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repository.save(newEmployee);
       });

    EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
  }

  @DeleteMapping("/employees/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
