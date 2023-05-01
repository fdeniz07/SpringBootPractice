package com.tpe.dto;


import com.tpe.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    @NotNull(message = "Product name can not be null")
    private String productName;

    @NotNull(message = "Brand name can not be null")
    private String brand;

    @NotNull(message = "Price can not be null")
    private double price;

    private Long customerId;
}
