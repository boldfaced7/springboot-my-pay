package com.mypay.bankaccount.application.port.in;

import com.mypay.bankaccount.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountQuery {
    RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
