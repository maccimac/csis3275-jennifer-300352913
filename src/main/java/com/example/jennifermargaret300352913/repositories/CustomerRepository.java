package com.example.jennifermargaret300352913.repositories;

import com.example.jennifermargaret300352913.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
