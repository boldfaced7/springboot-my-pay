package com.mypay.common;

public interface ValidationHandler<T> {
    void validate(T command);
    int getValidationOrder();
}
