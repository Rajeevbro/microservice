package com.rajeevcode.account.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccountsDto {


    @NotEmpty(message = "Account Number cannot be null or Empty")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be null or Empty")
    private String accountType;

    @NotEmpty(message = "Brnach Address cannot be empty")
    private String branchAddress;
}
