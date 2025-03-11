package com.rajeevcode.account.mapper;

import com.rajeevcode.account.dto.AccountsDto;
import com.rajeevcode.account.entity.Accounts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccountMapper {

    public static AccountsDto mapToAccountDto(Accounts accounts)
    {
        return AccountsDto.builder()
                .accountNumber(accounts.getAccountNumber())
                .branchAddress(accounts.getBranchAddress())
                .accountType(accounts.getAccountType())
                .build();
    }


    public static Accounts mapToAccounts(AccountsDto acountsDto, Accounts accounts)
    {

        return accounts.toBuilder()
                .accountNumber(acountsDto.getAccountNumber())
                .accountType(acountsDto.getAccountType())
                .branchAddress(acountsDto.getBranchAddress())
                .build();
    }
}
