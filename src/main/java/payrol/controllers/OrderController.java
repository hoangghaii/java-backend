package payrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import payrol.assembler.OrderModelAssembler;
import payrol.exception.NotFoundException;
import payrol.models.EErrorType;
import payrol.repository.OrderRepository;
import payrol.models.EStatus;
import payrol.models.Order;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class OrderController {

  private final OrderModelAssembler assembler;

  OrderController(OrderModelAssembler assembler) {
    this.assembler = assembler;
  }

  @Autowired
  OrderRepository repository;

  @GetMapping("/orders")
  public CollectionModel<EntityModel<Order>> all() {
    List<EntityModel<Order>> orders = repository.findAll().stream()
        .map(assembler::toModel).collect(Collectors.toList());

    return CollectionModel.of(orders,
        linkTo(methodOn(OrderController.class).all()).withSelfRel());
  }

  @GetMapping("/orders/{id}")
  public EntityModel<Order> one(@PathVariable Long id) {
    Order order = repository.findById(id).orElseThrow(() -> new NotFoundException(id, EErrorType.order));

    return assembler.toModel(order);
  }

  @PostMapping("/orders")
  ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order) {
    order.setStatus(EStatus.IN_PROGRESS);
    Order newOrder = repository.save(order);

    return ResponseEntity.created(
        linkTo(methodOn(OrderController.class)
            .one(newOrder.getId()))
            .toUri()
        ).body(assembler.toModel(newOrder));
  }

  @DeleteMapping("orders/{id}/cancel")
  public ResponseEntity<?> cancel(@PathVariable Long id) {
    Order order = repository.findById(id).orElseThrow(() -> new NotFoundException(id, EErrorType.order));

    if(order.getStatus() == EStatus.IN_PROGRESS) {
      order.setStatus(EStatus.CANCELLED);
      return ResponseEntity.ok(assembler.toModel(repository.save(order)));
    }

    return ResponseEntity
        .status(HttpStatus.METHOD_NOT_ALLOWED)
        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        .body(Problem.create()
                .withTitle("Method not allowed")
                .withDetail(
                    "You can't cancel an order that is in the " +
                    order.getStatus() +
                    " status"));
  }

  @PutMapping("orders/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable Long id) {
    Order order = repository.findById(id).orElseThrow(() -> new NotFoundException(id, EErrorType.order));

    if(order.getStatus() == EStatus.IN_PROGRESS) {
      order.setStatus(EStatus.COMPLETED);
      return ResponseEntity.ok(assembler.toModel(repository.save(order)));
    }

    return ResponseEntity
        .status(HttpStatus.METHOD_NOT_ALLOWED)
        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        .body(Problem.create()
            .withTitle("Method not allowed")
            .withDetail(
                "You can't cancel an order that is in the " +
                order.getStatus() +
                " status"));
  }
}
