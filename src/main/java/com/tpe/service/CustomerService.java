package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDto;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        boolean isExistCustomer = customerRepository.existsByEmail(customer.getEmail());

        if (isExistCustomer) {
            throw new ConflictException("Customer already exist by email : " + customer.getEmail());
        }
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers;

        //return customerRepository.findAll(); //--> Tek satirda yukaridaki kodu yazma
    }

    public Customer getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + id));
        return customer;
        //return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + id));
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    public CustomerDto getCustomerDTOById(Long id) {
        Customer customer = getCustomerById(id);
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.setFirstName(customer.getFirstName());
//        customerDto.setLastName(customer.getLastName());
//        customerDto.setEmail(customer.getEmail());
//        customerDto.setPhone(customer.getPhone());

        CustomerDto customerDto = new CustomerDto(customer);
        return customerDto;
    }


    public void updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = getCustomerById(id);

        boolean isExistEmail = customerRepository.existsByEmail(customerDto.getEmail());

        if (isExistEmail && !customer.getEmail().equals(customerDto.getEmail())) {
            throw new ConflictException("Customer already exist by email : " + customerDto.getEmail());
        }

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());

        customerRepository.save(customer);
    }

    public Page<Customer> getAllCustomerPage(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage;
    }

    public List<Customer> getCustomerByFirstName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        return customers;
    }


    public List<Customer> getCustomersByFirstNameAndLastName(String firstName, String lastName) {
        List<Customer> customerList = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        return customerList;
    }

    public List<Customer> getAllCustomersFirstNameLike(String name) {
        String lowerName = name.toLowerCase();
        List<Customer> customers = customerRepository.findAllByFirstNameLike(lowerName);
        return customers;
    }

    public List<Customer> getAllCustomersLikeFirstNameOrLastName(String firstNameOrLastName) {
        String lowerFirstNameOrLastName = firstNameOrLastName.toLowerCase();
        List<Customer> customers = customerRepository.findAllByNameOrLastNameLike(lowerFirstNameOrLastName);
        return customers;
    }
}
