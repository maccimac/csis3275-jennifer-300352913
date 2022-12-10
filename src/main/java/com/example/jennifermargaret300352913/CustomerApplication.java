package com.example.jennifermargaret300352913;
import com.example.jennifermargaret300352913.entities.Customer;
import com.example.jennifermargaret300352913.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
@SpringBootApplication
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "Margaret", "A1", new Date()));
            customerRepository.save(new Customer(null, "Joshua", "B1", new Date()));
            customerRepository.findAll().forEach(p -> {
                System.out.println(p.getName());
            });
        };

    }
}