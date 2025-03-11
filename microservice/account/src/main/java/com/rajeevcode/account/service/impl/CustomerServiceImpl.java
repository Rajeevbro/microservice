package com.rajeevcode.account.service.impl;

import com.rajeevcode.account.constants.AccountsConstants;
import com.rajeevcode.account.dto.AccountsDto;
import com.rajeevcode.account.dto.CustomerDto;
import com.rajeevcode.account.entity.Accounts;
import com.rajeevcode.account.entity.Customer;
import com.rajeevcode.account.exception.CustomerAlreadyExistsException;
import com.rajeevcode.account.exception.ResourceNotFoundException;
import com.rajeevcode.account.mapper.AccountMapper;
import com.rajeevcode.account.mapper.CustomerMapper;
import com.rajeevcode.account.repository.AccountsRepository;
import com.rajeevcode.account.repository.CustomerRepository;
import com.rajeevcode.account.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;



    @Transactional
    public void createCustomer(CustomerDto customerDto)
    {
        Customer customer = new Customer();
        Customer customerToBeSaved = CustomerMapper.mapToCustomer(customerDto,customer);
        if(customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent())
        {
            throw new CustomerAlreadyExistsException("Customer already exists");

        }
        customerRepository.save(customerToBeSaved);
        accountsRepository.save(createAccount(customerToBeSaved));
    }



    private Accounts createAccount(Customer customer) {
        return Accounts.builder()
                .branchAddress(AccountsConstants.ADDRESSES)
                .accountNumber(1000000L + new Random().nextInt())
                .accountType(AccountsConstants.SAVINGS)
                .customerId(customer.getCustomerId())
                .build();
    }


    public CustomerDto fetchCustomerByMobileNumber(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));

        Accounts accounts =accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("accounts","customerId",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        AccountsDto accountsDto = AccountMapper.mapToAccountDto(accounts);
        customerDto.setAccountsDto(accountsDto);
        return customerDto;

    }

    public  boolean updateaccounts(CustomerDto customerDto)
    {

       Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(
               ()->new ResourceNotFoundException("customer", "mobileNumber", customerDto.getMobileNumber()));
       Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
               ()->new ResourceNotFoundException("accounts", "customerId", customer.getCustomerId().toString()));
       customerRepository.save(CustomerMapper.mapToCustomer(customerDto,customer));
       accountsRepository.save(AccountMapper.mapToAccounts(customerDto.getAccountsDto(),accounts));
        return true;

    }





}
