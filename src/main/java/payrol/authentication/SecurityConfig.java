package payrol.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import payrol.Employee.Employee;
import payrol.Employee.EmployeeRepository;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        // No need authentication.
        .antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/register").permitAll()
        // Need authentication.
        .anyRequest().authenticated()
        .and()
        // Add Filter 1 - JWTLoginFilter
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        // Add Filter 2 - JWTAuthenticationFilter
        .addFilterBefore(new JWTAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class)
        .logout().permitAll();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//    String password = "123";

//    String encrytedPassword = this.passwordEncoder().encode(password);
//    System.out.println(encrytedPassword);
//
//
    InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
        mngConfig = auth.inMemoryAuthentication();
//
//    // Defines 2 users, stored in memory.
//    // ** Spring BOOT >= 2.x (Spring Security 5.x)
//    // Spring auto add ROLE_
//    UserDetails u1 = User.withUsername("tom").password(encrytedPassword).roles("USER").build();
//    UserDetails u2 = User.withUsername("jerry").password(encrytedPassword).roles("USER").build();
//
//    mngConfig.withUser(u1);
//    mngConfig.withUser(u2);

    System.out.println(this.employeeRepository.findAll());

    this.employeeRepository.findAll().forEach(employee -> {
      String encrytedPassword = this.passwordEncoder().encode(employee.getPassword());
      UserDetails userDetails = User
          .withUsername(employee.getLastName())
          .password(encrytedPassword)
          .roles(employee.getRole())
          .build();
      mngConfig.withUser(userDetails);
    });

  }
}
