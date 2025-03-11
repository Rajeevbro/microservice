package com.rajeevcode.account.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerDto {


    @NotEmpty(message = "Name cannot be null")
    @Size(min = 3, max = 35 , message = "The length of the customer name should be between 3 to 35 character long")
    private String name;

    @Email(message = "Must be a valid Email Address")
    private String email;

    @NotNull(message = "Mobile number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
