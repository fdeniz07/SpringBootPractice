package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDto;
import com.tpe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //1-Spring boot u selamlama http://localhost:8080/customers/greet

    @GetMapping("/greet")
    public String greetSpringBoot() {
        return "Hello Spring Boot";
    }

    //2-Customer oluşturma/kaydetme http://localhost:8080/customers/save
    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer customer) {

        customerService.saveCustomer(customer);
        String message = "Customer saved successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //3-Tüm customerları getirme->http://localhost:8080/customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {

        List<Customer> customers = customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }

    //4-Id ile customer getirme->http://localhost:8080/customers/1
    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    //5-id ile customer getirme->http://localhost:8080/customers/custom?id=1
    @GetMapping("/custom")
    public ResponseEntity<CustomerDto> getCustomerDtoById2(@RequestParam("id") Long id) {
        CustomerDto customerDTO = customerService.getCustomerDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }

    //6-id ile customer silme->http://localhost:8080/customers/delete/1
    //Customer is deleted successfully mesajı dönsün
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {

        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer is deleted successfully");
    }


    //task 7: id ile customerı update etme->http://localhost:8080/customers/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {

        customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok("Customer is updated successfuly");
    }

    //paginated list
    //6-Tüm customerları page page getirme- >http://localhost:8080/customers/page?=1&size=2&sort=id&direction=asc
    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getAllCustomerPage(@RequestParam("page") int page, //kacinci sayfa
                                                             @RequestParam("size") int size, //sayfada kac adet veri
                                                             @RequestParam("sort") String prop, //hangi alana göre siralama
                                                             @RequestParam("direction") Sort.Direction direction) //artan/azalan siralama
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<Customer> customers = customerService.getAllCustomerPage(pageable);
        return ResponseEntity.ok(customers);

    }

    //7.Name bilgisi ile customer getirme- >http://localhost:8080/customers/query?firstName=Jack
    @GetMapping("/query")
    public ResponseEntity<List<Customer>> getCustomerByFirstName(@RequestParam("firstName") String firstName) {
        List<Customer> customers = customerService.getCustomerByFirstName(firstName);
        return ResponseEntity.ok(customers);
    }


    //8.firstName ve lastName bilgisi ile customer getirme->http://localhost:8080/customers/fullquery?firstName=Jack&lastName=Sparrow
    @GetMapping("/fullquery")
    public ResponseEntity<List<Customer>> getCustomerByFirstNameAndLastName(@RequestParam("firstName") String firstName,
                                                                       @RequestParam("lastName") String lastName) {
        List<Customer> customerList = customerService.getCustomersByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(customerList);
    }
}
