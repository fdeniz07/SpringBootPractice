package com.tpe.dto;

import com.tpe.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull(message = "First name can not be null") //null olamaz ama bosluk olur
    @NotBlank(message = "First name can not be blank")
    @NotEmpty(message = "First name can not be empty")
    @Size(min=2,max = 50)
    private String firstName;

    @NotNull(message = "First name can not be null") //null olamaz ama bosluk olur
    @NotBlank (message = "First name can not be blank")
    @NotEmpty(message = "First name can not be empty")
    @Size(min=2,max = 50)
    private String lastName;


    @Email
    private String email;

    private String phone;

    public CustomerDto(Customer customer){
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }

}
