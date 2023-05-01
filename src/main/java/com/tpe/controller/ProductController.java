package com.tpe.controller;

import com.tpe.dto.ProductDto;
import com.tpe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //1.product oluşturma kaydetme > http://localhost:8080/product/save
    @PostMapping("/save")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
        return new ResponseEntity<>("Product saved successfully", HttpStatus.CREATED);
    }
}
