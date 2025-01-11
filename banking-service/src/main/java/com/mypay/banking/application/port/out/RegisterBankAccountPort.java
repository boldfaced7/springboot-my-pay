package com.mypay.banking.application.port.out;

import com.mypay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccount createRegisteredBankAccount(
            RegisteredBankAccount registeredBankAccount);
}
