package com.rajeevcode.account.repository;

import com.rajeevcode.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Optional<Customer> findByMobileNumber(String mobileNumber);
}
