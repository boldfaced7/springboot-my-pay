package com.mypay.bankaccount.application.port.out;

import com.mypay.bankaccount.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccount save(
            RegisteredBankAccount registeredBankAccount);
}
