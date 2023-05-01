package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.domain.Product;
import com.tpe.dto.ProductDto;
import com.tpe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private CustomerService customerService;


    public void saveProduct(ProductDto productDto) {

        Product product = new Product();
        Customer customer = customerService.getCustomerById(productDto.getCustomerId());
        product.setProductName(productDto.getProductName());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setCustomer(customer);
        productRepository.save(product);

    }
}
