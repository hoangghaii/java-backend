package payrol.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import payrol.controllers.EmployeeController;
import payrol.models.Employee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<
    Employee,
    EntityModel<Employee>
    >
{
  @Override
  public EntityModel<Employee> toModel(Employee employee) {
    return EntityModel.of(employee,
        WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employee"));
  }
}
