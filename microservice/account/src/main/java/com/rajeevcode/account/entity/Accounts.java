package com.rajeevcode.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Accounts extends BaseEntity {


    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;


    @Column(name = "account_type")
    private String accountType;


    @Column(name = "branch_address")
    private String branchAddress;



}
