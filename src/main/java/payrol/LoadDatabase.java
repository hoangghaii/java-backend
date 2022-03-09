package payrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payrol.models.*;
import payrol.repository.EmployeeRepository;
import payrol.repository.OrderRepository;

@Configuration
public class LoadDatabase {
  private  static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
    return args -> {
      employeeRepository.save(Employee.builder()
          .firstName("Nguyen")
          .lastName("Hoang Hai")
          .email("nhhai@gmail.com")
          .password("123")
          .role("ADMIN")
          .build());
      employeeRepository.save(Employee.builder()
          .firstName("Ngo")
          .lastName("Thi Ngan")
          .email("ntngan@gmail.com")
          .password("123")
          .role("USER")
          .build());
      employeeRepository.findAll().forEach(employee -> log.info("Preloading " + employee));

      orderRepository.save(Order.builder().description("MacBook Pro").status(EStatus.COMPLETED).build());
      orderRepository.save(Order.builder().description("iPhone").status(EStatus.IN_PROGRESS).build());
      orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
    };
  }
}
