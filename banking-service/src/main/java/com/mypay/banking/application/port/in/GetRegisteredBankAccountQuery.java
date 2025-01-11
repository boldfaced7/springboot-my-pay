package com.mypay.banking.application.port.in;

import com.mypay.banking.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountQuery {
    RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
