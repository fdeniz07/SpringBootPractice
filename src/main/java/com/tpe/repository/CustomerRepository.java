package com.tpe.repository;

import com.tpe.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Opsiyonel
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT c FROM Customer c WHERE lower(c.firstName) like %:pfirstName%")
    List<Customer> findAllByFirstNameLike(@Param("pfirstName") String lowerName);

    @Query("SELECT c FROM Customer c WHERE lower(c.firstName) like %:pfirstName% or lower(c.lastName) like %:plastName%")
    List<Customer> findAllByNameOrLastNameLike(String lowerFirstNameOrLastName);
}
