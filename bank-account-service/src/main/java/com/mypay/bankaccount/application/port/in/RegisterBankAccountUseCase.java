package com.mypay.bankaccount.application.port.in;

import com.mypay.bankaccount.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
