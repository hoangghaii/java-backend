package payrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payrol.models.Employee;
import payrol.repository.EmployeeRepository;
import payrol.models.Order;
import payrol.repository.OrderRepository;
import payrol.models.EStatus;

@Configuration
public class LoadDatabase {
  private  static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
    return  args -> {
      employeeRepository.save(new Employee("Nguyen","Hoang Hai","nhhai@gmail.com", "123", "ADMIN"));
      employeeRepository.save(new Employee("Ngo", "Thi Ngan", "ntngan@gmail.com", "123", "USER"));
      employeeRepository.findAll().forEach(employee -> log.info("Preloading " + employee));

      orderRepository.save(new Order("MacBook Pro", EStatus.COMPLETED));
      orderRepository.save(new Order("iPhone", EStatus.IN_PROGRESS));
      orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
    };
  }
}
