package com.rajeevcode.account.service;

import com.rajeevcode.account.dto.CustomerDto;

public interface CustomerService {


    void createCustomer(CustomerDto customerDto);
    CustomerDto fetchCustomerByMobileNumber(String mobileNumber);
    boolean updateaccounts(CustomerDto customerDto);
}
