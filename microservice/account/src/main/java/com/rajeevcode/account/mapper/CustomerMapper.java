package com.rajeevcode.account.mapper;


import com.rajeevcode.account.dto.CustomerDto;
import com.rajeevcode.account.entity.Customer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerMapper {



    public static CustomerDto mapToCustomerDto(Customer customer) {

        return  CustomerDto.builder()
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .name(customer.getName())
                .build();
    }


    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        return customer.toBuilder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();
    }
}
