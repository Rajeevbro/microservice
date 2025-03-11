package com.rajeevcode.account.controller;

import com.rajeevcode.account.constants.AccountsConstants;
import com.rajeevcode.account.dto.CustomerDto;
import com.rajeevcode.account.dto.ResponseDto;
import com.rajeevcode.account.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {


    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createAccounts(@RequestBody @Valid CustomerDto customerDto)
    {
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.builder()
                        .statusMessage(AccountsConstants.MESSAGE_201)
                        .statusCode(AccountsConstants.STATUS_201)
                .build());
    }



    @GetMapping("/fetch ")
    public ResponseEntity<CustomerDto> findAccountsByPhoneNumber(@RequestParam String phoneNumber)
    {
        CustomerDto customerDto = customerService.fetchCustomerByMobileNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }


}
